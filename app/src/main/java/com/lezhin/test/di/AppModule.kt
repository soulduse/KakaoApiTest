package com.lezhin.test.di

import com.lezhin.test.ui.detail.DetailViewModel
import com.lezhin.test.ui.main.MainAdapter
import com.lezhin.test.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { DetailViewModel() }

    single { MainAdapter() }
}
