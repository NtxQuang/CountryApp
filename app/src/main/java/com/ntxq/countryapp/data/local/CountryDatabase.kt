package com.ntxq.countryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ntxq.countryapp.model.SavedCountry

@Database(entities = [SavedCountry::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    CountryDatabase::class.java,
                    "country-database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
    }
    abstract fun countryDao(): CountryDao
}