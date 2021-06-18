package com.temicode.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.bind
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temicode.countries.R
import com.temicode.countries.model.Country
import com.temicode.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_country.view.*

class MainActivity : AppCompatActivity(), CountryListAdapter.CountriesOnClickListener {

    lateinit var viewModel: ListViewModel

    private var country = arrayListOf<Country>()

    private var countriesAdapter = CountryListAdapter(arrayListOf(), this@MainActivity )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        // call the refresh function
        viewModel.refresh()

        countries_recyclerView.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, {countries ->
            countries?.let {
                countries_recyclerView.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, {isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE}
        })

        viewModel.loading.observe(this, {isLoading->
            isLoading?.let { loading_view.visibility = if (it) View.VISIBLE else View.GONE
            if (it) {
                list_error.visibility = View.GONE
                countries_recyclerView.visibility = View.GONE
                }
            }

        })
    }

    override fun OnClick(item: Country, position: Int) {
        Toast.makeText(this, item.countryName, Toast.LENGTH_LONG).show()
    }

}
