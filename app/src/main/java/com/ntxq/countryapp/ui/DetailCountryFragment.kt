package com.ntxq.countryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.ntxq.countryapp.databinding.FragmentDetailCountryBinding
import com.ntxq.countryapp.model.SavedCountry
import com.ntxq.countryapp.util.loadImageUrl
import com.ntxq.countryapp.util.popBackstack
import com.ntxq.countryapp.viewmodel.SavedCountryViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DetailCountryFragment : BaseFragment<FragmentDetailCountryBinding>() {
    private val savedCountryViewModel by activityViewModel<SavedCountryViewModel>()
    private val navArgs by navArgs<DetailCountryFragmentArgs>()
    private val country by lazy { navArgs.country }
    override fun getVB(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailCountryBinding {
        return FragmentDetailCountryBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        savedCountryViewModel.listCountry.observe(viewLifecycleOwner) {
            binding.loadingView.isVisible = false
            binding.buttonSave.isActivated =
                savedCountryViewModel.checkSavedCountry(country.name.official)
        }

        binding.toolbar.setNavigationOnClickListener {
            popBackstack()
        }

        binding.textTitle.text = country.name.common
        binding.textCommonName.setInfo(country.name.common)
        binding.textOfficialName.setInfo(country.name.official)
        binding.textDescriptionFLag.setInfo(country.flag.description)

        binding.imageFlag.loadImageUrl(country.flag.url)

        binding.buttonSave.setOnClickListener {
            binding.loadingView.isVisible = true
            val savedCountry = SavedCountry(
                officialName = country.name.official,
                commonName = country.name.common,
                flagUrl = country.flag.url,
                flagDescription = country.flag.description
            )
            if (it.isActivated) {
                savedCountryViewModel.deleteSavedCountry(savedCountry)
            } else {
                savedCountryViewModel.saveCountry(savedCountry)
            }
        }
    }

}