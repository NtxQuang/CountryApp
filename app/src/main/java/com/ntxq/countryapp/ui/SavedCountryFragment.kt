package com.ntxq.countryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ntxq.countryapp.R
import com.ntxq.countryapp.adapter.SavedCountryAdapter
import com.ntxq.countryapp.databinding.FragmentSavedCountryBinding
import com.ntxq.countryapp.model.Country
import com.ntxq.countryapp.model.CountryFlag
import com.ntxq.countryapp.model.CountryName
import com.ntxq.countryapp.util.navigate
import com.ntxq.countryapp.util.popBackstack
import com.ntxq.countryapp.viewmodel.SavedCountryViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SavedCountryFragment : BaseFragment<FragmentSavedCountryBinding>() {
    private val viewModel by activityViewModel<SavedCountryViewModel>()
    private val countryAdapter by lazy {
        SavedCountryAdapter(
            onItemCountryCLick = {
                navigate(
                    R.id.detailCountryFragment,
                    DetailCountryFragmentArgs(
                        Country(
                            CountryFlag(it.flagUrl, it.flagDescription),
                            CountryName(it.commonName, it.officialName)
                        )
                    ).toBundle()
                )
            },
            onButtonDeleteClick = {
                OptionsDialogFragment.newInstance {
                    viewModel.deleteSavedCountry(it)
                }.show(childFragmentManager, null)
            })
    }

    override fun getVB(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSavedCountryBinding {
        return FragmentSavedCountryBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.recyclerViewCountry.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = countryAdapter
        }
        viewModel.listCountry.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            binding.emptyStateLayout.isVisible = it.isNullOrEmpty()
            countryAdapter.submitList(it)
        }
        binding.toolbar.setNavigationOnClickListener {
            popBackstack()
        }
    }

}