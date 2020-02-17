package com.bakayapps.coctailsbook.mvp.view

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.RecipeActivity
import com.bakayapps.coctailsbook.adapter.RecipesAdapter
import com.bakayapps.coctailsbook.adapter.ViewPagerAdapter
import com.bakayapps.coctailsbook.di.MainContract
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bakayapps.coctailsbook.utils.EnumOfRV
import com.bakayapps.coctailsbook.utils.StartSnapHelper
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), MainContract.View {
    companion object {
        const val TAG = "HomeFragment"
    }

    lateinit var adapterTop: RecipesAdapter
    lateinit var adapterBottom: RecipesAdapter

    val mPresenter: MainContract.Presenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.fillListData()

        viewPager2.adapter = ViewPagerAdapter {
            mPresenter.clickPosition(it, EnumOfRV.VIEWPAGER)
        }

        val typeface = Typeface.createFromAsset(requireContext().assets, "playfair.ttf")
        popular_recp.typeface = typeface
        new_recp.typeface = typeface

        recyclerViewPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerViewNew.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                requireContext(),
                RecipeActivity::class.java
            ).putExtra("groupNum", num).putExtra("recipeNum", numRecipe)
        )
    }
}