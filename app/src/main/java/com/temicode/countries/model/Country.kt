package com.temicode.countries.model

import androidx.cardview.widget.CardView
import com.google.gson.annotations.SerializedName

data class Country(
    // this will map the variable name to the name on the api
    @SerializedName("name")
    val countryName: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("flagPNG")
    val flag: String?,

    val cardView: CardView
)