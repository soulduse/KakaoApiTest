package com.lezhin.test.ui.search

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.MenuItem
import com.lezhin.test.BR
import com.lezhin.test.R
import com.lezhin.test.databinding.ActivitySearchBinding
import com.lezhin.test.ui.base.BaseActivity
import com.lezhin.test.utils.Global
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity: BaseActivity<ActivitySearchBinding, SearchViewModel>(), SearchNavigation {
    private val searchViewModel: SearchViewModel by viewModel()

    private var shouldInitRecyclerView = true

    private lateinit var activitySearchBinding: ActivitySearchBinding

    override fun getBindingVariable(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun getViewModel(): SearchViewModel = searchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = getViewDataBinding()
        initToolbar()
        initSearch()
    }

    private fun initToolbar() {
        setSupportActionBar(activitySearchBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        }
    }

    private fun initSearch() {
        activitySearchBinding.searchView.apply {
            isFocusable = true
            isIconified = false
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(!query.isNullOrBlank()) {
//                        searchViewModel.insertKeyword(query!!)
                        goToMainForSearching(query)
                    }

                    return true
                }

                override fun onQueryTextChange(keyword: String): Boolean {
                    val trimKeyword = keyword.replace(" ", "")
                    if(trimKeyword.isEmpty()) {
//                        findAllKeywords(this@SearchActivity::updateRecyclerView)
                    }
                    return true
                }
            })
        }

//        findAllKeywords(this@SearchActivity::updateRecyclerView)
    }
/*
    private fun findAllKeywords(onListen: (List<SearchItem>) -> Unit) {
        searchViewModel.getKeywords { liveKeyowords ->
            liveKeyowords.observe(this, Observer { keywords ->
                val updateWithKeywords = mutableListOf<SearchItem>()
                keywords?.forEach {
                    updateWithKeywords.add(SearchItem(it, this))
                }

                onListen(updateWithKeywords)
            })
        }
    }



    private fun updateRecyclerView(searchItems: List<SearchItem>) {
        fun init() {
            launch(UI) {
                activitySearchBinding.rvSearchedHistory.apply {
                    layoutManager = LinearLayoutManager(this@SearchActivity)
                    adapter = SearchAdapter()
                }

                shouldInitRecyclerView = false
            }
        }

        fun updateItems() = searchSection.update(searchItems)

        if(shouldInitRecyclerView)
            init()
        else
            updateItems()
    }


   */

    override fun goToMainForSearching(keyword: String?) {
        val resultIntent = Intent().apply {
            putExtra(Global.EXTRA_KEYWORD, keyword)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
