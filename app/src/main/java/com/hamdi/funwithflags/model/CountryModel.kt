package com.hamdi.funwithflags.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class CountryModel(
    @Expose
    val name: String,
    @Expose
    val alpha2Code: String,
    @Expose
    val alpha3Code: String,
    @Expose
    val capital: String,
    @Expose
    val region: String,
    @Expose
    val subregion: String,
    @Expose
    val population: String,
    @Expose
    val nativeName: String,
    @Expose
    val flag: String,
    @Expose
    val callingCodes: ArrayList<String>,
    @Expose
    val altSpellings: ArrayList<String>,
    @Expose
    val latlng: ArrayList<Float>,
    @Expose
    val currencies: ArrayList<CurrenciesModel>,
    @Expose
    val languages: ArrayList<LanguagesModel>
) :Serializable

data class CurrenciesModel(
    @Expose
    val code: String,
    @Expose
    val name: String,
    @Expose
    val symbol: String
):Serializable

data class LanguagesModel(
    @Expose
    val name: String,
    @Expose
    val nativeName: String
):Serializable