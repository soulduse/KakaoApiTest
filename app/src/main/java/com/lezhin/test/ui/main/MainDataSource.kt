package com.lezhin.test.ui.main

import android.arch.paging.PositionalDataSource
import com.dave.network.Network
import com.dave.network.NetworkState
import com.lezhin.test.api.ApiProvider
import com.lezhin.test.api.data.Document
import com.lezhin.test.api.data.ImageApiData
import com.lezhin.test.utils.DLog

class MainDataSource(
    private val keyword: String,
    private var updateState: (NetworkState<ImageApiData>) -> Unit): PositionalDataSource<Document>() {

    private var pageNo = 1
    private var isEnd = true

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Document>) {
        val call = ApiProvider.apiProvider().searchImages(query = keyword)
        Network.request(
            call = call,
            doOnSubscribe = { updateState(NetworkState.Loading()) },
            doOnTerminate = { updateState(NetworkState.Init()) },
            success = {
                isEnd = it.meta.isEnd
                updateState(NetworkState.Success(it))
                DLog.d("Data Observer data loadInit --->${it.documents}")
                callback.onResult(it.documents, 1, it.meta.pageableCount)
            },
            error = { updateState(NetworkState.Error(it)) }
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Document>) {
        if (isEnd) {
            sendEmpty(callback)
            return
        }
        plusPageNo()

        val call = ApiProvider.apiProvider().searchImages(query = keyword, page = pageNo)
        Network.request(
            call = call,
            doOnSubscribe = { updateState(NetworkState.Loading()) },
            doOnTerminate = { updateState(NetworkState.Init()) },
            success = {
                isEnd = it.meta.isEnd
                updateState(NetworkState.Success(it))
                callback.onResult(it.documents)
            },
            error = { updateState(NetworkState.Error(it)) }
        )
    }

    private fun plusPageNo() {
        pageNo += 1
    }

    private fun sendEmpty(callback: LoadRangeCallback<Document>) {
        val emptyContents = arrayListOf<Document>()
        callback.onResult(emptyContents)
    }
}
