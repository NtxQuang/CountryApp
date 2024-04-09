package com.ntxq.countryapp.data

import com.ntxq.countryapp.data.local.CountryDao
import com.ntxq.countryapp.data.network.CountryApiService
import com.ntxq.countryapp.model.Country
import com.ntxq.countryapp.model.SavedCountry

class AppRepositoryImpl(
    private val apiService: CountryApiService,
    private val countryDao: CountryDao
) : AppRepository {
    override suspend fun getListCountry(): List<Country> {
        return apiService.getListCountry()
    }

    override suspend fun getListSavedCountry(): List<SavedCountry> {
        return countryDao.getAll()
    }

    override suspend fun saveCountry(country: SavedCountry) {
        countryDao.insert(country)
    }

    override suspend fun deleteSavedCountry(country: SavedCountry) {
        countryDao.delete(country)
    }
}