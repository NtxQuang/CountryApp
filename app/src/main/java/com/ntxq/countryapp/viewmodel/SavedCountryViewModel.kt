package com.ntxq.countryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntxq.countryapp.data.AppRepository
import com.ntxq.countryapp.model.SavedCountry
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedCountryViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _listCountry =
        MutableLiveData<List<SavedCountry>>()
    val listCountry: LiveData<List<SavedCountry>> get() = _listCountry

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(
            Dispatchers.IO + CoroutineExceptionHandler
            { context, throwable ->
                throwable.printStackTrace()
            }) {
            _listCountry.postValue(appRepository.getListSavedCountry())
        }
    }

    fun checkSavedCountry(officialName: String): Boolean {
        return _listCountry.value?.map { it.officialName }?.contains(officialName) ?: false
    }

    fun saveCountry(savedCountry: SavedCountry) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { context, throwable -> throwable.printStackTrace() }) {
            appRepository.saveCountry(savedCountry)
            _listCountry.postValue(appRepository.getListSavedCountry())
        }
    }

    fun deleteSavedCountry(savedCountry: SavedCountry) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { context, throwable -> throwable.printStackTrace() }) {
            appRepository.deleteSavedCountry(savedCountry)
            _listCountry.postValue(appRepository.getListSavedCountry())
        }
    }
}