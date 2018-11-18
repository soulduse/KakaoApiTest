package com.lezhin.test.di

import android.preference.PreferenceManager
import com.lezhin.test.db.AppDatabase
import com.lezhin.test.ui.detail.DetailViewModel
import com.lezhin.test.ui.main.MainAdapter
import com.lezhin.test.ui.main.MainViewModel
import com.lezhin.test.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel() }

    single { MainAdapter() }

    // Room Database
    single { AppDatabase.getDatabase(androidContext()) }
    single { PreferenceManager.getDefaultSharedPreferences(androidApplication()) }
}
