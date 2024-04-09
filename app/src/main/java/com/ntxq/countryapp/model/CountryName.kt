package com.ntxq.countryapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryName(
    @Json(name = "common") val common: String,
    @Json(name = "official") val official: String
) : Parcelable