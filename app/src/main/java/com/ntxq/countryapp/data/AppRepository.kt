package com.ntxq.countryapp.data

import com.ntxq.countryapp.model.Country
import com.ntxq.countryapp.model.SavedCountry

interface AppRepository {
    suspend fun getListCountry(): List<Country>

    suspend fun getListSavedCountry(): List<SavedCountry>

    suspend fun saveCountry(country: SavedCountry)

    suspend fun deleteSavedCountry(country: SavedCountry)

}