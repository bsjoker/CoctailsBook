package com.bakayapps.coctailsbook.mvp.presenter

import androidx.core.content.ContextCompat
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.di.MainContract
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bakayapps.coctailsbook.utils.EnumOfRV

class MainPresenter(val mView: MainContract.View) : MainContract.Presenter {
    companion object {
        const val TAG = "MainPresenter"
    }

    lateinit var num: IntArray
    lateinit var numRecipe: IntArray
    override fun fillListData() {

    }

    override fun clickPosition(pos: Int, type: EnumOfRV) {

    }

}