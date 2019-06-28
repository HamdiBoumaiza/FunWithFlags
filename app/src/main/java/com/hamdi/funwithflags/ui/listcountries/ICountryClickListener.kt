package com.hamdi.funwithflags.ui.listcountries

import com.hamdi.funwithflags.model.CountryModel

interface ICountryClickListener {
    fun clickItem(countryModel: CountryModel)
}