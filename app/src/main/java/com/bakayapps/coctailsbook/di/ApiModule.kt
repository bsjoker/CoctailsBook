package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.iCoctailApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single (createdAtStart = false) { get<Retrofit>().create(iCoctailApi::class.java) }
}