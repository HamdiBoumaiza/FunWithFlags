package com.hamdi.funwithflags.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hamdi.funwithflags.data.Client
import com.hamdi.funwithflags.data.Services
import com.hamdi.funwithflags.model.CountryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CountryRepository {

    @SuppressLint("CheckResult")
    fun getMoviesWS(page: Int): LiveData<List<CountryModel>> {
        val data = MutableLiveData<List<CountryModel>>()
        val apiService = Client().getClient().create(Services::class.java)

        apiService.getAllCountries()
            .retry(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                data.setValue(response)

            }, { throwable ->
                Timber.e("onError ws :: " + throwable.message)
            })
        return data
    }


}