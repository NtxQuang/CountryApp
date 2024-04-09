package com.ntxq.countryapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @Json(name = "flags") val flag: CountryFlag, @Json(name = "name") val name: CountryName
) : Parcelable

@Entity
data class SavedCountry(
    @PrimaryKey val officialName: String,
    @ColumnInfo val commonName: String,
    @ColumnInfo val flagUrl: String,
    @ColumnInfo val flagDescription: String
)


