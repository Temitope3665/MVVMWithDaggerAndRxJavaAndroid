package com.temicode.countries.model

import com.temicode.countries.diy.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {

    //private val BASE_URL = "https://raw.githubusercontent.com"

    @Inject
    lateinit var api: CountriesApi

    // where we are going to build the service
    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }

}