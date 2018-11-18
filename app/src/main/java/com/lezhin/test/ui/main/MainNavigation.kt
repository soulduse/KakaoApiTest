package com.lezhin.test.ui.main

import com.lezhin.test.api.data.Document

interface MainNavigation {
    fun updateUI()

    fun handleError(throwable: Throwable?)
}
