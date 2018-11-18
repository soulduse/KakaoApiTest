package com.lezhin.test.utils

import android.arch.paging.PagedList

object PagedUtil {

    fun getConfig(loadHint: Int = 10, size: Int = 10, prefetch: Int = 5): PagedList.Config{
        return PagedList.Config.Builder()
                    .setInitialLoadSizeHint(loadHint)
                    .setPageSize(size)
                    .setPrefetchDistance(prefetch)
                    .setEnablePlaceholders(false)
                    .build()
    }
}
