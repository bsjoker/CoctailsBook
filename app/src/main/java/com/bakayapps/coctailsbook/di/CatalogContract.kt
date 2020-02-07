package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.models.RecipeModelForRV

interface CatalogContract {
    interface View {
        fun setDataToRV(groupsForRV: ArrayList<RecipeModelForRV>)
        fun startNewActivity(pos: Int)

    }

    interface Presenter {
        fun fillDataForRV()
        fun clickToItem(pos: Int)

    }
}