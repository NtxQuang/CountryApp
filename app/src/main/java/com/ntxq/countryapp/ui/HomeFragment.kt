package com.ntxq.countryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ntxq.countryapp.R
import com.ntxq.countryapp.databinding.FragmentHomeBinding
import com.ntxq.countryapp.util.navigate

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getVB(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.buttonListCountry.setOnClickListener {
            navigate(R.id.listCountryFragment)
        }

        binding.buttonCollection.setOnClickListener {
            navigate(R.id.savedCountryFragment)
        }
    }

}