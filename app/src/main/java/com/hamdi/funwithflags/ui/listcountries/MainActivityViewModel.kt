package com.hamdi.funwithflags.ui.listcountries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hamdi.funwithflags.model.CountryModel
import com.hamdi.funwithflags.repository.CountryRepository


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val photosRepository = CountryRepository()

    fun getArrayPhotos(page: Int): LiveData<List<CountryModel>> {
        return photosRepository.getMoviesWS(page)
    }
}