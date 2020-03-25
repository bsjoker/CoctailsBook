package com.bakayapps.coctailsbook.data.remote.DataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.bakayapps.coctailsbook.data.remote.repository.CoctailsRepositoryImpl
import com.bakayapps.coctailsbook.data.remote.response.ShortItemCoctailResponse
import com.bakayapps.coctailsbook.iCoctailsDBApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class NetworkDataSource(
    private val repository: CoctailsRepositoryImpl,
    private val query: String,
    private val scope: CoroutineScope
): PageKeyedDataSource<Int, ShortItemCoctailResponse>() {
    private var supervisorJob = SupervisorJob()
    //private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null //Keep the last query just in case it fails

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ShortItemCoctailResponse>) {
        retryQuery = { loadInitial(params, callback) }
        executeQuery(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ShortItemCoctailResponse>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        executeQuery(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ShortItemCoctailResponse>) {
        //Not implemented
    }

    private fun executeQuery(
        page: Int,
        callback: (List<ShortItemCoctailResponse>) -> Unit
    ) {
        //networkState.postValue(NetworkState.RUNNING)
        scope.launch(supervisorJob) {
            val recipes = repository.getCurrentCategoryCoctail(query, page)
            retryQuery = null
            //networkState.postValue(NetworkState.SUCCESS)
            callback(recipes)
        }
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    fun refresh() =
        this.invalidate()

//    private val _downloadedShortList : MutableLiveData<List<ShortItemCoctail>> = MutableLiveData(arrayListOf())
//    val downloadedShortList: LiveData<List<ShortItemCoctail>>
//        get() = _downloadedShortList
//
//    fun fetchShortListCoctails(query: String) {
//        val params = mutableMapOf<String, String>().apply {
//            this["q"] = query
//        }
//
//        addToDisposable(coctailsDBApi.getShortListCoctails(params).with()
//            .doOnSubscribe {
//            //    _refreshing.value = true
//                }
//            .doOnSuccess {
//            //    _refreshing.value = false
//            }
//            .doOnError {
//            //    _refreshing.value = false
//            }
//            .subscribe({
//                _downloadedShortList.value = it.drinks
//            }, {
//                // handle errors
//            }))
//    }
//
//    fun addToDisposable(disposable: Disposable) {
//        disposables.add(disposable)
//    }
}