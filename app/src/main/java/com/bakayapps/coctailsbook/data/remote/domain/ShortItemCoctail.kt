package com.bakayapps.coctailsbook.data.remote.domain

import com.google.gson.annotations.SerializedName

data class ShortItemCoctail (
    @SerializedName("strDrink") val nameCoctail: String,
    @SerializedName("strDrinkThumb") val thumbnail: String,
    @SerializedName("idDrink") val id: String
)