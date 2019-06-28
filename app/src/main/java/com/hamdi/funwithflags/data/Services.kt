package com.hamdi.funwithflags.data

import com.hamdi.funwithflags.model.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface Services {

    @GET("all")
    fun getAllCountries(): Single<ArrayList<CountryModel>>

}