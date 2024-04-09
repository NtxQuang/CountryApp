package com.ntxq.countryapp.data.network

import com.ntxq.countryapp.model.Country
import retrofit2.http.GET

interface CountryApiService {
    @GET("all?fields=name,flags")
    suspend fun getListCountry(): List<Country>

}