package com.bakayapps.coctailsbook.data.remote.repository

import androidx.lifecycle.LiveData
import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.bakayapps.coctailsbook.data.remote.response.ShortItemCoctailResponse
import kotlinx.coroutines.Deferred

interface CoctailsRepository {
    suspend fun getCurrentCategoryCoctail(category: String) : Deferred<ShortItemCoctailResponse>
    //suspend fun getCurrentCategoryCoctail(category: String): LiveData<out List<ShortItemCoctail>>
}