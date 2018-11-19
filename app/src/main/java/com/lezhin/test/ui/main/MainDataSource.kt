package com.lezhin.test.ui.main

import android.arch.paging.PositionalDataSource
import com.dave.network.Network
import com.dave.network.NetworkState
import com.lezhin.test.api.ApiProvider
import com.lezhin.test.api.data.Document
import com.lezhin.test.api.data.ImageApiData
import com.lezhin.test.utils.DLog

class MainDataSource(private val keyword: String,
                     private var updateState: (NetworkState<ImageApiData>) -> Unit) : PositionalDataSource<Document>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Document>) {
        val call = ApiProvider.apiProvider().searchImages(query = keyword)
        Network.request(
            call = call,
            doOnSubscribe = { updateState(NetworkState.Loading()) },
            doOnTerminate = { updateState(NetworkState.Init()) },
            success = {
                updateState(NetworkState.Success(it))
                DLog.d("Data Observer data loadInit --->${it.documents}")
                callback.onResult(it.documents, 0, it.meta.pageableCount)
            },
            error = { updateState(NetworkState.Error(it)) }
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Document>) {
        DLog.d("DataSource ${params.startPosition}, ${params.loadSize}, ${params.loadSize + params.startPosition}")
        val call = ApiProvider.apiProvider().searchImages(query = keyword, page = getPageNo(params))
        Network.request(
            call = call,
            doOnSubscribe = { updateState(NetworkState.Loading()) },
            doOnTerminate = { updateState(NetworkState.Init()) },
            success = {
                updateState(NetworkState.Success(it))
                callback.onResult(it.documents)
            },
            error = { updateState(NetworkState.Error(it)) }
        )
    }

    /**
     * 페이징 라이브러리를 사용할때, API의 구조에 따라서 이부분의 계산은 달라질 수 있다.
     */
    private fun getPageNo(params: LoadRangeParams): Int {
        val sum = params.startPosition + params.loadSize
        if (sum <= FIRST_PAGE_NO) {
            return FIRST_PAGE_NO
        }
        return sum / PAGE_DIVIDE_NO
    }

    companion object {
        private const val FIRST_PAGE_NO = 1
        private const val PAGE_DIVIDE_NO = 10
    }
}
