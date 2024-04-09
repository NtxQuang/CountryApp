package com.ntxq.countryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ntxq.countryapp.model.Country
import com.ntxq.countryapp.model.SavedCountry

@Dao
interface CountryDao {
    @Insert
     fun insert(country: SavedCountry)

    @Delete
     fun delete(country: SavedCountry)

    @Query("select * from SavedCountry")
     fun getAll(): List<SavedCountry>
}