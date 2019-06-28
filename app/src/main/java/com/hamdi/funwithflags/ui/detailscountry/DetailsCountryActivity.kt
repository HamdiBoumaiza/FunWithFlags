package com.hamdi.funwithflags.ui.detailscountry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader
import com.hamdi.funwithflags.R
import com.hamdi.funwithflags.model.CountryModel
import com.hamdi.funwithflags.ui.listcountries.MainActivity.Companion.COUNTRY_EXTRA
import kotlinx.android.synthetic.main.activity_details_country.*


class DetailsCountryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_country)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val countryModel = intent.getSerializableExtra(COUNTRY_EXTRA) as CountryModel
        setData(countryModel)
    }

    private fun setData(countryModel: CountryModel) {
        nameCountryTV.text = getString(R.string.country_name, countryModel.name)
        currencyTV.text = getString(R.string.country_currency, countryModel.currencies[0].name)
        capitalTV.text = getString(R.string.country_capital, countryModel.capital)
        populationTV.text = getString(R.string.country_population, countryModel.population)
        regionTV.text = getString(R.string.country_region, countryModel.region, countryModel.subregion)
        calling_codeTV.text = getString(R.string.country_calling_code, countryModel.callingCodes[0])
        languageTV.text = getString(R.string.country_language, countryModel.languages[0].name)
        SvgLoader.pluck().with(this).load(countryModel.flag, countryIV)

        openMapsBTN.setOnClickListener {
            openMaps(countryModel.name, countryModel.latlng[0].toString(), countryModel.latlng[1].toString())
        }
    }

    private fun openMaps(namePlace: String, lat: String, lng: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$lat,$lng($namePlace)"))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        SvgLoader.pluck().close()
    }
}
