package com.bakayapps.coctailsbook

import com.bakayapps.coctailsbook.data.ShortItemCoctail
import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel
import com.bakayapps.coctailsbook.data.remote.response.ShortItemCoctailResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface iCoctailApi {

    @GET("api/json/v1/1/filter.php")
    fun getShortListCoctails(@Query("c") query: String): Single<ShortItemCoctailResponse>

    @GET
    fun getData(@Url url: String): Single<List<ArticlesModel>>
}