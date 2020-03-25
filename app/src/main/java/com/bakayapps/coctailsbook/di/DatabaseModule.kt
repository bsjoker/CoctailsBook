package com.bakayapps.coctailsbook.di


import com.bakayapps.coctailsbook.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {AppDatabase.getAppDataBase(get())}
    single (createdAtStart = false){ get<AppDatabase>().articlesDao() }
}