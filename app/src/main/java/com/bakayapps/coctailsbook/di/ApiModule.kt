package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.iCoctailsDBApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single (createdAtStart = false) { get<Retrofit>().create(iCoctailsDBApi::class.java) }
}