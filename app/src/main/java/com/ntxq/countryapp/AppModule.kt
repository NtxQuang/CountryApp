package com.ntxq.countryapp

import com.ntxq.countryapp.Constant.BASE_URL
import com.ntxq.countryapp.data.AppRepository
import com.ntxq.countryapp.data.AppRepositoryImpl
import com.ntxq.countryapp.data.local.CountryDatabase
import com.ntxq.countryapp.util.provideCountryApiService
import com.ntxq.countryapp.util.provideMoshi
import com.ntxq.countryapp.util.provideOkHttpClient
import com.ntxq.countryapp.viewmodel.ListCountryViewModel
import com.ntxq.countryapp.viewmodel.SavedCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppRepositoryImpl(get(), get()) }
    single<AppRepository> { get<AppRepositoryImpl>() }
}

val viewModelModule = module {
    viewModel { ListCountryViewModel(get()) }
    viewModel { SavedCountryViewModel(get()) }
}

val retrofitModule = module {
    single {
        provideMoshi()
    }
    single {
        provideOkHttpClient(get())
    }
    single {
        provideCountryApiService(BASE_URL, get(), get())
    }
}

val roomModule = module {
    single { CountryDatabase.getInstance(get()) }
    single { get<CountryDatabase>().countryDao() }
}