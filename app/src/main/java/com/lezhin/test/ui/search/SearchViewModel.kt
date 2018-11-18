package com.lezhin.test.ui.search

import android.arch.lifecycle.LiveData
import com.lezhin.test.db.AppDatabase
import com.lezhin.test.db.search.Search
import com.lezhin.test.ui.base.BaseViewModel
import com.lezhin.test.utils.DLog
import kotlinx.coroutines.experimental.async

class SearchViewModel(private val appDatabase: AppDatabase): BaseViewModel<SearchNavigation>() {

    fun getKeywords(keywords: (LiveData<List<String>>) -> Unit) {
        async {
            keywords(appDatabase.searchDao().findAllKeywords())
        }
    }

    fun deleteSearch() {
        async {
            appDatabase.searchDao().deleteAll()
        }
    }

    fun insertKeyword(keyword: String) {
        async {
            DLog.w("insert keyword : $keyword")
            appDatabase.searchDao().insertSearch(Search(keyword = keyword))
        }
    }
}
