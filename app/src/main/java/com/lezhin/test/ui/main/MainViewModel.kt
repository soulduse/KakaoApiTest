package com.lezhin.test.ui.main

import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.databinding.ObservableBoolean
import com.dave.network.NetworkState
import com.lezhin.test.api.data.Document
import com.lezhin.test.api.data.ImageApiData
import com.lezhin.test.ui.base.BaseViewModel
import com.lezhin.test.utils.DLog
import com.lezhin.test.utils.PagedUtil

class MainViewModel: BaseViewModel<MainNavigation>() {

    val isLoading = ObservableBoolean(true)

    val isEmptyData = ObservableBoolean(false)

    private val updateViewData: (NetworkState<ImageApiData>) -> Unit = {
        when(it) {
            is NetworkState.Init -> { hideProgress() }
            is NetworkState.Loading -> { showProgress() }
            is NetworkState.Success -> {
                DLog.w("Origin Data Observer ---> ${it.item.documents}")
                isEmptyData.set(it.item.documents.isEmpty())
            }
            is NetworkState.Error -> {
                hideProgress()
                getNavigator()?.handleError(it.throwable)
                isEmptyData.set(true)
            }
        }
    }

    fun getLivePagedBuilder(keyword: String) =
        LivePagedListBuilder<Int, Document>(object : DataSource.Factory<Int, Document>() {
            override fun create(): DataSource<Int, Document> {
                return MainDataSource(keyword, updateViewData)
            }
        }, PagedUtil.getConfig())


    private fun setIsEmptyData(isEmpty: Boolean) {
        isEmptyData.set(isEmpty)
    }

    private fun showProgress() {
        isLoading.set(true)
    }

    private fun hideProgress() {
        isLoading.set(false)
    }

    fun onRefresh() {
        getNavigator()?.updateUI()
    }
}
