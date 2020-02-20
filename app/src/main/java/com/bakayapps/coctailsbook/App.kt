package com.bakayapps.coctailsbook

import android.app.Application
import com.bakayapps.coctailsbook.di.KoinModule.loadModule
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(loadModule())
        }

        RxJavaPlugins.setErrorHandler { throwable -> }

//        // OneSignal Initialization
//        OneSignal.startInit(this)
//            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//            .unsubscribeWhenNotificationsAreDisabled(true)
//            .init()

        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}