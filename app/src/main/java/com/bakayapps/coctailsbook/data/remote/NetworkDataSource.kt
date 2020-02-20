package com.bakayapps.coctailsbook.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bakayapps.coctailsbook.data.remote.domain.ShortItemCoctail
import com.bakayapps.coctailsbook.data.remote.response.ShortItemCoctailResponse
import com.bakayapps.coctailsbook.extensions.with
import com.bakayapps.coctailsbook.iCoctailsDBApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class NetworkDataSource(
    private val coctailsDBApi: iCoctailsDBApi
) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _downloadedShortList : MutableLiveData<List<ShortItemCoctail>> = MutableLiveData(arrayListOf())
    val downloadedShortList: LiveData<List<ShortItemCoctail>>
        get() = _downloadedShortList

    fun fetchShortListCoctails(query: String) {
        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
        }

        addToDisposable(coctailsDBApi.getShortListCoctails(params).with()
            .doOnSubscribe {
            //    _refreshing.value = true
                }
            .doOnSuccess {
            //    _refreshing.value = false
            }
            .doOnError {
            //    _refreshing.value = false
            }
            .subscribe({
                _downloadedShortList.value = it.drinks
            }, {
                // handle errors
            }))
    }

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}