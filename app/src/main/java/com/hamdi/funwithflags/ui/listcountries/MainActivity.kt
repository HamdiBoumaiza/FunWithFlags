package com.hamdi.funwithflags.ui.listcountries

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmadrosid.svgloader.SvgLoader
import com.hamdi.funwithflags.R
import com.hamdi.funwithflags.model.CountryModel
import com.hamdi.funwithflags.ui.detailscountry.DetailsCountryActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ICountryClickListener  {
    companion object {
        const val COUNTRY_EXTRA = "COUNTRY_KEY"
    }

    private var searchView: SearchView? = null
    private var countriesAdapter: CountriesAdapter? = null
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun clickItem(countryModel: CountryModel) {
        startActivity<DetailsCountryActivity>(COUNTRY_EXTRA to countryModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        mainActivityViewModel.getArrayPhotos(1).observe(this, Observer<List<CountryModel>> { list ->
            if (list != null) initRecyclerView(list)
        })
    }

    private fun initRecyclerView(arrayMovies: List<CountryModel>) {
        rvSources.layoutManager = GridLayoutManager(this, 2)

        countriesAdapter = CountriesAdapter(this, arrayMovies, this)
        rvSources.adapter = countriesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        SvgLoader.pluck().close()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                countriesAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                countriesAdapter!!.filter.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
