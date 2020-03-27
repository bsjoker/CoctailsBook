package com.bakayapps.coctailsbook

import android.app.Application
import com.bakayapps.coctailsbook.di.*
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@App)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule, loadingModule, mainModule, catalogGroupModule))
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
        fun createCoctailApi(): iCoctailApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.thecocktaildb.com/")
                .client(client)
                .build()

            return retrofit.create(iCoctailApi::class.java)
        }

        lateinit var instance: App
            private set
    }
}