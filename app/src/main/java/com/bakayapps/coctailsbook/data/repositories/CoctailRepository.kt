package com.bakayapps.coctailsbook.database

import androidx.lifecycle.LiveData
import com.bakayapps.coctailsbook.data.*
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel

object CoctailRepository {
    private val local = LocalDataHolder
    private val network = NetworkDataholder

    fun loadCoctailRecipe(coctailId: String): LiveData<List<Any>?>{
        return network.loadCoctailRecipe(coctailId)
    }

    fun getCoctail(coctailId: String): LiveData<CoctailData?>{
        return local.findCoctail(coctailId)
    }

    fun loadCoctailPersonalInfo(coctailId: String): LiveData<CoctailPersonalInfo?>{
        return local.findCoctailPersonalInfo(coctailId)
    }

    fun getAppSettings(): LiveData<AppSettings> = local.getAppSettings() //from preferences
    fun updateSettings(appSettings: AppSettings){
        local.updateAppSettings(appSettings)
    }

    fun updateCoctailPersonalInfo(info: CoctailPersonalInfo) {
        local.updateCoctailPersonalInfo(info)
    }
}