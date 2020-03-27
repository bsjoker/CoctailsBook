package com.bakayapps.coctailsbook.di

import com.bakayapps.coctailsbook.viewmodels.CoctailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoctailViewModel(get())}
}