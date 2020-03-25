package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.data.remote.repository.CoctailsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { CoctailsRepositoryImpl(get()) }
}