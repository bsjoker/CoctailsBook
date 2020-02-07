package com.bakayapps.coctailsbook.mvp.view

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.RecipeActivity
import com.bakayapps.coctailsbook.adapter.RecipesAdapter
import com.bakayapps.coctailsbook.adapter.ViewPagerAdapter
import com.bakayapps.coctailsbook.di.MainContract
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bakayapps.coctailsbook.utils.EnumOfRV
import com.bakayapps.coctailsbook.utils.StartSnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {
    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var adapterTop: RecipesAdapter
    lateinit var adapterBottom: RecipesAdapter

    val mPresenter: MainContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.fillListData()

        viewPager2.adapter = ViewPagerAdapter {
            mPresenter.clickPosition(it, EnumOfRV.VIEWPAGER)
        }

        val typeface = Typeface.createFromAsset(assets, "playfair.ttf")
        popular_recp.typeface = typeface
        new_recp.typeface = typeface

        recyclerViewPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewNew.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        navigation.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_catalog) {
                startActivity(Intent(this@MainActivity, CatalogActivity::class.java))
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setDataToRV(
        popularRecipeForRVS: ArrayList<RecipeModelForRV>,
        newRecipeForRVS: ArrayList<RecipeModelForRV>
    ) {
        adapterTop = RecipesAdapter(popularRecipeForRVS) {
            Log.d(TAG, "clicked at : $it")
            mPresenter.clickPosition(it, EnumOfRV.RECYCLERVIEW_POPULAR)
        }
        recyclerViewPopular.adapter = adapterTop

        adapterBottom = RecipesAdapter(newRecipeForRVS) {
            Log.d(TAG, "clicked at : $it")
            mPresenter.clickPosition(it, EnumOfRV.RECYCLERVIEW_NEW)
        }
        recyclerViewNew.adapter = adapterBottom

        val snapHelperPopular = StartSnapHelper()
        snapHelperPopular.attachToRecyclerView(recyclerViewPopular)

        val snapHelperNew = StartSnapHelper()
        snapHelperNew.attachToRecyclerView(recyclerViewNew)
    }

    override fun startNextActivity(num: Int, numRecipe: Int) {
        startActivity(
            Intent(
                this@MainActivity,
                RecipeActivity::class.java
            ).putExtra("groupNum", num).putExtra("recipeNum", numRecipe)
        )
    }
}
