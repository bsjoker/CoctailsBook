package com.bakayapps.coctailsbook.di

import android.content.Context
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel

interface LoadingContract {
    interface View {
        fun setArtictesViewModel(it: ArticlesModel)
        fun changeIcon(name: String)
        fun startNewActivity(value: Boolean)
    }

    interface Presenter{
        fun getRecipesFromServer()
        fun hasConnection(context: Context): Boolean

        fun disposeDisposable()
    }
}