package com.bakayapps.coctailsbook.mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.adapter.CatalogAdapter
import com.bakayapps.coctailsbook.di.CatalogContract
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import kotlinx.android.synthetic.main.fragment_catalog.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CatalogFragment : Fragment(), CatalogContract.View {
    companion object {
        const val TAG = "CatalogFragment"
    }

    val mPresenter: CatalogContract.Presenter by inject { parametersOf(this) }

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

        mPresenter.fillDataForRV()

//        navigation.selectedItemId = R.id.navigation_catalog
//        navigation.setOnNavigationItemSelectedListener { item ->
//            if (item.itemId == R.id.navigation_home) {
//                startActivity(Intent(this@CatalogActivity, MainActivity::class.java))
//            }
//            return@setOnNavigationItemSelectedListener true
//        }
    }

    override fun setDataToRV(groupsForRV: ArrayList<RecipeModelForRV>) {
        recyclerViewCatalogMain.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCatalogMain.adapter = CatalogAdapter(groupsForRV) {
            Log.d(TAG, "clicked at : $it")
            mPresenter.clickToItem(it)
        }
    }

//    override fun startNewActivity(pos: Int) {
//        if(pos>1){
//            startActivity(Intent(this@CatalogActivity, CatalogGroupeActivity::class.java).putExtra("pos", pos+1))
//        } else {
//            startActivity(Intent(this@CatalogActivity, CatalogGroupeActivity::class.java).putExtra("pos", pos))
//        }
//    }
}