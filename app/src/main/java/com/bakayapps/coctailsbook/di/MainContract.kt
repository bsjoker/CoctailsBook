package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bakayapps.coctailsbook.utils.EnumOfRV

interface MainContract {
    interface View {
        fun startNextActivity(num: Int, numRecipe: Int)
        fun setDataToRV(popularRecipeForRVS: ArrayList<RecipeModelForRV>, newRecipeForRVS: ArrayList<RecipeModelForRV>)
    }

    interface Presenter {
        fun fillListData()
        fun clickPosition(pos: Int, type: EnumOfRV)
    }
}