package com.bakayapps.coctailsbook.mvp.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import android.util.Log
import com.bakayapps.coctailsbook.App
import com.bakayapps.coctailsbook.mvp.view.LoadingActivity
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.di.LoadingContract
import com.bakayapps.coctailsbook.models.ArticlesModel
import com.bakayapps.coctailsbook.utils.PreferencesHelper

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InvalidClassException

class LoadingPresenter(val mView: LoadingContract.View): LoadingContract.Presenter {
    companion object {
        const val TAG = "LoadingPresenter"
    }

    var disposable: Disposable? = null
    var i: Long = 0
    private var mCountry: String = ""
    private var catchData = false
    private var isStart = false
    private var mState = false
    private var accessCountry = false


    private val mIBeautyDataApi by lazy {
        App.create()
    }

    private val mICountryApi by lazy {
        App.createCountry()
    }

    override fun getRecipesFromServer() {
        if (hasConnection(App.instance.applicationContext)) {
            disposable = mIBeautyDataApi.getData("info")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        var mArticles = ArrayList<ArticlesModel>()
                        mArticles.addAll(result)

                        result.forEach {
                            i++
                            it.id = i
                            mView.setArtictesViewModel(it)
                            //articlesViewModel.insert(it)
                            Log.d(TAG, "Data from result: " + it.title + " " + it.id)
                        }
                        catchData = true
                    },
                    { error -> Log.d(TAG, "Error to try catch data!") }
                )
        } else {
            Log.d(TAG, "No connect")
        }
    }

    override fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

    fun getCountry(): String {
        val telephonyManager = App.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        Log.d(LoadingActivity.TAG, "Country: " + telephonyManager.simCountryIso.toLowerCase())
        return telephonyManager.simCountryIso.toLowerCase()
    }

    override fun disposeDisposable() {
        disposable?.dispose()
    }
}