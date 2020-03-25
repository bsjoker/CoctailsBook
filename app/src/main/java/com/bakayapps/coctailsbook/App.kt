package com.bakayapps.coctailsbook

import android.app.Application
import com.bakayapps.coctailsbook.di.*
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@App)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule, loadingModule, mainModule, catalogModule, catalogGroupModule))
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