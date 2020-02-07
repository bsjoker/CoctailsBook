package com.bakayapps.coctailsbook.models

import android.graphics.drawable.Drawable

data class RecipeModelForRVGroup(
    val pic: Drawable?,
    val title: String,
    val rate: Int,
    var description: String
    )
