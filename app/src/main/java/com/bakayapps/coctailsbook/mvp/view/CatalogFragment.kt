package com.bakayapps.coctailsbook.mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.adapter.CatalogAdapter
import com.bakayapps.coctailsbook.viewmodels.CoctailViewModel
import com.bakayapps.coctailsbook.di.CatalogContract
import com.bakayapps.coctailsbook.models.RecipeModelForRV

class CatalogFragment : Fragment(), CatalogContract.View {
    companion object {
        const val TAG = "CatalogFragment"
    }

    //private val model by viewModel<CoctailViewModel>()

    //val mPresenter: CatalogContract.Presenter by inject { parametersOf(this) }

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

        //observeViewModelData()
        //mPresenter.fillDataForRV()

//        navigation.selectedItemId = R.id.navigation_catalog
//        navigation.setOnNavigationItemSelectedListener { item ->
//            if (item.itemId == R.id.navigation_home) {
//                startActivity(Intent(this@CatalogActivity, MainActivity::class.java))
//            }
//            return@setOnNavigationItemSelectedListener true
//        }
    }

    override fun setDataToRV(groupsForRV: ArrayList<RecipeModelForRV>) {

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