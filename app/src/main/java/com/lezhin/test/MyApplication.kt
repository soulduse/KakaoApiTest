package com.lezhin.test

import android.app.Application
import com.lezhin.test.di.appModule
import org.koin.android.ext.android.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

    companion object {
        val DEBUG = BuildConfig.DEBUG
        fun getGlobalContext(): MyApplication = getGlobalContext()
    }
}
