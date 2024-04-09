package com.ntxq.countryapp.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ntxq.countryapp.R
import com.ntxq.countryapp.adapter.ListCountryAdapter
import com.ntxq.countryapp.databinding.FragmentListCountryBinding
import com.ntxq.countryapp.util.navigate
import com.ntxq.countryapp.util.popBackstack
import com.ntxq.countryapp.viewmodel.ListCountryViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ListCountryFragment : BaseFragment<FragmentListCountryBinding>() {
    private val viewModel by activityViewModel<ListCountryViewModel>()
    private val countryAdapter by lazy {
        ListCountryAdapter {
            binding.textInputSearch.setText("")
            navigate(R.id.detailCountryFragment, DetailCountryFragmentArgs(it).toBundle())
        }
    }

    override fun getVB(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCountryBinding {
        return FragmentListCountryBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            popBackstack()
        }
        binding.recyclerViewCountry.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = countryAdapter
        }
        viewModel.listCountry.observe(viewLifecycleOwner) {
            binding.loadingView.isVisible = false
            countryAdapter.setData(it)
        }

        binding.textInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                countryAdapter.filter.filter(text.toString())
            }

        })
    }

}