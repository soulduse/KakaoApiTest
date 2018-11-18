package com.lezhin.test.ui.main

interface MainNavigation {
    fun updateUI()

    fun handleError(throwable: Throwable?)
}
