package com.bakayapps.coctailsbook.mvp.view

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.RecipeActivity
import com.bakayapps.coctailsbook.adapter.RecipesAdapter
import com.bakayapps.coctailsbook.adapter.ViewPagerAdapter
import com.bakayapps.coctailsbook.data.ShortItemCoctail
import com.bakayapps.coctailsbook.viewmodels.CoctailState
import com.bakayapps.coctailsbook.viewmodels.CoctailViewModel
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bakayapps.coctailsbook.utils.EnumOfRV
import com.bakayapps.coctailsbook.utils.StartSnapHelper
import com.bakayapps.coctailsbook.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: CoctailViewModel

    lateinit var adapterTop: RecipesAdapter
    lateinit var adapterBottom: RecipesAdapter

    lateinit var num: IntArray
    lateinit var numRecipe: IntArray

    private val popularRecipeForRVS: ArrayList<RecipeModelForRV> = ArrayList()
    private val newRecipeForRVS: List<ShortItemCoctail> = emptyList()

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

        val vmFactory = ViewModelFactory("0")
        viewModel = ViewModelProvider(requireActivity(), vmFactory).get(CoctailViewModel::class.java)
        viewModel.observState(this){
            renderUI(it)
        }

        viewPager2.adapter = ViewPagerAdapter {
            clickPosition(it, EnumOfRV.VIEWPAGER)
        }

        val typeface = Typeface.createFromAsset(requireContext().assets, "playfair.ttf")
        popular_recp.typeface = typeface
        new_recp.typeface = typeface

        recyclerViewPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerViewNew.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun renderUI(coctailState: CoctailState) {
        //popular_recp.text = if (coctailState.isLoadingRecipe) "loading" else coctailState.listShortItemCoctailsByCategory.first().nameCoctail
        if (!coctailState.isLoadingRecipe) setDataToRV(coctailState.listShortItemCoctailsByCategory, coctailState.listShortItemCoctailsByCategory)
    }

    fun setDataToRV(
        popularRecipeForRVS: List<ShortItemCoctail>,
        newRecipeForRVS: List<ShortItemCoctail>
    ) {
        adapterTop = RecipesAdapter(popularRecipeForRVS) {
            Log.d(TAG, "clicked at : $it")
            clickPosition(it, EnumOfRV.RECYCLERVIEW_POPULAR)
        }

        recyclerViewPopular.adapter = adapterTop

        adapterBottom = RecipesAdapter(newRecipeForRVS) {
            Log.d(TAG, "clicked at : $it")
            clickPosition(it, EnumOfRV.RECYCLERVIEW_NEW)
        }
        recyclerViewNew.adapter = adapterBottom

        val snapHelperPopular = StartSnapHelper()
        snapHelperPopular.attachToRecyclerView(recyclerViewPopular)

        val snapHelperNew = StartSnapHelper()
        snapHelperNew.attachToRecyclerView(recyclerViewNew)
    }

    fun clickPosition(pos: Int, type: EnumOfRV) {
        when (type) {
            EnumOfRV.VIEWPAGER -> {
                num = intArrayOf(4, 3, 0, 3, 4)
                numRecipe = intArrayOf(29, 0, 2, 16, 0)
            }

            EnumOfRV.RECYCLERVIEW_POPULAR ->
            {
                num = intArrayOf(3, 3, 4, 0, 4, 3)
                numRecipe = intArrayOf(6, 7, 30, 9, 11, 4)
            }

            EnumOfRV.RECYCLERVIEW_NEW ->
            {
                num = intArrayOf(3, 4, 0, 4, 3, 3)
                numRecipe = intArrayOf(10, 37, 4, 19, 15, 17)
            }
        }
        startNextActivity(num[pos], numRecipe[pos])
    }

    fun startNextActivity(num: Int, numRecipe: Int) {
        startActivity(
            Intent(
                requireContext(),
                RecipeActivity::class.java
            ).putExtra("groupNum", num).putExtra("recipeNum", numRecipe)
        )
    }

    private inline fun <reified T> T.get() = T::class.java.simpleName
}