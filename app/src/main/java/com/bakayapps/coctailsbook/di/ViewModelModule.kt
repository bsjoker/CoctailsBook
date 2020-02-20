package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.database.ArticlesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticlesViewModel(get(), androidApplication())}
}