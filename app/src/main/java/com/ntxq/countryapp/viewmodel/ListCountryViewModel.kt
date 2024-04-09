package com.ntxq.countryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntxq.countryapp.data.AppRepository
import com.ntxq.countryapp.model.Country
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListCountryViewModel(private val appRepository: AppRepository) : ViewModel() {
    private val _listCountry = MutableLiveData<List<Country>>()
    val listCountry: LiveData<List<Country>> get() = _listCountry

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler
        { context, throwable ->
            throwable.printStackTrace()
        }) {
            _listCountry.postValue(appRepository.getListCountry())
        }
    }
}