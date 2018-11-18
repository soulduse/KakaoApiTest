package com.lezhin.test.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.lezhin.test.BR
import com.lezhin.test.R
import com.lezhin.test.databinding.ActivityMainBinding
import com.lezhin.test.ui.base.BaseActivity
import com.lezhin.test.utils.SizeUtil
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigation {
    private val mainViewModel: MainViewModel by viewModel()
    private val mainAdapter: MainAdapter by inject()
    private val searchDelayHandler: Handler = Handler()

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun getBindingVariable(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SizeUtil.initSizeData(this)
        mainViewModel.setNavigator(this)
        activityMainBinding = getViewDataBinding()
        setup()
    }

    private fun setup() {
        activityMainBinding.rvImages.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        searchImage(BASE_KEYWORD)
        initSearch()
    }

    private fun initSearch() {
        activityMainBinding.searchView.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(!query.isNullOrBlank()) {
                        searchImage(query)
                    }
                    return true
                }

                override fun onQueryTextChange(keyword: String): Boolean {
                    autoSearch(keyword)
                    return true
                }
            })
        }
    }

    private fun searchImage(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            return
        }
        mainViewModel.getLivePagedBuilder(keyword!!)
            .build()
            .observe(this, Observer { documents ->
                mainAdapter.submitList(documents)
            })
    }

    private fun autoSearch(keyword: String) {
        searchDelayHandler.run {
            removeCallbacksAndMessages(null)
            postDelayed({
                searchImage(keyword)
            }, ONE_SECOND)
        }
    }

    override fun updateUI() {
        // Pull to Refresh 를 넣었다면 사용했을 메소드
    }

    override fun handleError(throwable: Throwable?) {
        toast(R.string.error_api)
    }

    companion object {
        private const val ONE_SECOND: Long = 1000L
        private const val BASE_KEYWORD = "레진코믹스"
    }
}
