package com.temicode.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.temicode.countries.R
import com.temicode.countries.model.Country
import com.temicode.countries.util.getProgressDrawable
import com.temicode.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>, var onClickListener: CountriesOnClickListener): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    // update countries
    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries.get(position), onClickListener)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val countryName = view.name_of_countries
        private val capital = view.capital
        private val cardViewShow = view.cardView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind (country: Country, action: CountriesOnClickListener) {
            countryName.text = country.countryName
            capital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)

            cardViewShow.setOnClickListener {
                action.OnClick(country, adapterPosition)
            }

        }


//        init {
//            view.setOnClickListener {
//                val position = adapterPosition
//                onClickListener.OnClick(position)
//            }
//        }

    }

    interface CountriesOnClickListener {
        fun OnClick(item: Country, position: Int)
    }
}