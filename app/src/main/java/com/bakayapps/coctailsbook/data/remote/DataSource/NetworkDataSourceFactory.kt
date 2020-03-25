package com.bakayapps.coctailsbook.data.remote.DataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.bakayapps.coctailsbook.data.remote.repository.CoctailsRepositoryImpl
import com.bakayapps.coctailsbook.data.remote.response.ShortItemCoctailResponse
import kotlinx.coroutines.CoroutineScope


class NetworkDataSourceFactory(
    private val repository: CoctailsRepositoryImpl,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, ShortItemCoctailResponse>() {

    val source = MutableLiveData<NetworkDataSource>()

    override fun create(): DataSource<Int, ShortItemCoctailResponse> {
        val source = NetworkDataSource(repository, query, scope)
        this.source.postValue(source)
        return source
    }

    fun getQuery() = query

    fun getSource() = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.refresh()
    }

//    fun saveRecipePersistence(recipe: RecipeDB?) {
//        getSource()?.saveRecipePersistence(recipe)
//    }
}