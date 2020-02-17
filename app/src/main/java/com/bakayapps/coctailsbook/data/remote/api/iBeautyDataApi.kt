package com.bakayapps.coctailsbook

import com.bakayapps.coctailsbook.data.database.entity.ArticlesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface iBeautyDataApi {

    @GET
    fun getData(@Url url: String): Single<List<ArticlesModel>>
}