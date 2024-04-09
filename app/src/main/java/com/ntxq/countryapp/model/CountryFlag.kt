package com.ntxq.countryapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryFlag(
    @Json(name = "png") val url: String,
    @Json(name = "alt") val description: String
) : Parcelable