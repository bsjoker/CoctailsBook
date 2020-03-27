package com.bakayapps.coctailsbook.mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.adapter.CatalogAdapter
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import kotlinx.android.synthetic.main.fragment_catalog.*

class CatalogFragment : Fragment() {
    companion object {
        const val TAG = "CatalogFragment"
    }

    private val drawArray = intArrayOf(
        R.drawable.group_ordinary_drink,
        R.drawable.group_coctail,
        R.drawable.group_milk_shake,
        R.drawable.group_others,
        R.drawable.group_cocoa,
        R.drawable.group_shots,
        R.drawable.group_coffee_tea,
        R.drawable.group_homemade_liqueur,
        R.drawable.group_punch,
        R.drawable.group_beer,
        R.drawable.group_soft_soda
    )
    private val groupsForRV: ArrayList<RecipeModelForRV> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDataForRV()
    }

    fun fillDataForRV() {
        for (i in 0..10){
            groupsForRV.add(
                RecipeModelForRV(
                    ContextCompat.getDrawable(App.instance.applicationContext, drawArray[i])!!,
                    App.instance.resources.getStringArray(R.array.group_main_catalog)[i]
                )
            )
        }

        setDataToRV(groupsForRV)
    }

    fun clickToItem(pos: Int) {
        //mView.startNewActivity(pos)
    }

    fun setDataToRV(groupsForRV: ArrayList<RecipeModelForRV>) {
        recyclerViewCatalogMain.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCatalogMain.adapter = CatalogAdapter(groupsForRV) {
            Log.d(TAG, "clicked at : $it")
            //mPresenter.clickToItem(it)
        }
    }

//    private fun observeViewModelData() {
//        //model.networkState?.observe(this, Observer { repositoryRecyclerViewAdapter.updateNetworkState(it) })
//        Log.d(TAG, model.fetchRecipesByIngredients("Cocktail").toString())
//        model.coctails.observe(viewLifecycleOwner, Observer { model -> Log.d(TAG, "Observe + ${model}") })
//    }
//
//    override fun setDataToRV(groupsForRV: ArrayList<RecipeModelForRV>) {
//        recyclerViewCatalogMain.layoutManager = LinearLayoutManager(requireContext())
//        recyclerViewCatalogMain.adapter = CatalogAdapter(groupsForRV) {
//            Log.d(TAG, "clicked at : $it")
//            //mPresenter.clickToItem(it)
//        }
//    }

//    override fun startNewActivity(pos: Int) {
//        if(pos>1){
//            startActivity(Intent(this@CatalogActivity, CatalogGroupeActivity::class.java).putExtra("pos", pos+1))
//        } else {
//            startActivity(Intent(this@CatalogActivity, CatalogGroupeActivity::class.java).putExtra("pos", pos))
//        }
//    }
}