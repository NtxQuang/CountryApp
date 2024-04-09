package com.ntxq.countryapp.util

import android.content.Context
import com.ntxq.countryapp.data.network.CountryApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun provideOkHttpClient(context: Context) : OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideCountryApiService(
    baseUrl: String,
    moshi: Moshi,
    okHttpClient: OkHttpClient
): CountryApiService = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(CountryApiService::class.java)
