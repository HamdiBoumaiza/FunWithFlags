package com.hamdi.funwithflags.ui.listcountries

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.hamdi.funwithflags.BR
import com.hamdi.funwithflags.R
import com.hamdi.funwithflags.databinding.ItemCountryBinding
import com.hamdi.funwithflags.model.CountryModel
import java.util.*




class CountriesAdapter(
    private var monClickListener: ICountryClickListener,
    private var mListAllCountries: List<CountryModel>,
    private var mContext: Activity
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>(), Filterable {

    private var mListCountries: List<CountryModel> = mListAllCountries


    private lateinit var itemSourceBinding: ItemCountryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemSourceBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_country, parent, false)
        return ViewHolder(itemSourceBinding)
    }


    override fun getItemCount(): Int {
        return mListAllCountries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListAllCountries[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mListAllCountries = mListCountries
                } else {
                    val filteredList = ArrayList<CountryModel>()
                    for (row in mListCountries) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name.toLowerCase().contains(charString.toLowerCase()) || row.capital.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    mListAllCountries = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mListAllCountries
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mListAllCountries = filterResults.values as ArrayList<CountryModel>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {
        init {
            itemSourceBinding = view
            setIsRecyclable(false)
        }

        fun bind(model: CountryModel) {
            itemSourceBinding.country = model
            itemSourceBinding.setVariable(BR.clickListener, monClickListener)
            SvgLoader.pluck().with(mContext).load(model.flag, itemSourceBinding.image)
        }

    }
}