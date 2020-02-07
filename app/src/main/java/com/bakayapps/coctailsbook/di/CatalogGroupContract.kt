package com.bakayapps.coctailsbook.di

import android.graphics.drawable.Drawable
import com.bakayapps.coctailsbook.models.ArticlesModel
import com.bakayapps.coctailsbook.models.RecipeModelForRVGroup

interface CatalogGroupContract {
    interface View {
        fun updateRV(recipesForRV: ArrayList<RecipeModelForRVGroup>)
        fun setData(image: Drawable, text: String)
    }

    interface Presenter {
        fun fillDataFromDB(
            listArticles: List<ArticlesModel>,
            num: Int
        )

    }
}