package com.lezhin.test.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.lezhin.test.BR
import com.lezhin.test.R
import com.lezhin.test.databinding.ActivityMainBinding
import com.lezhin.test.ui.base.BaseActivity
import com.lezhin.test.ui.search.SearchActivity
import com.lezhin.test.utils.DLog
import com.lezhin.test.utils.Global
import com.lezhin.test.utils.SizeUtil
import org.jetbrains.anko.startActivityForResult
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigation {
    private val mainViewModel: MainViewModel by viewModel()

    private val mainAdapter: MainAdapter by inject()

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
        searchImage("레진코믹스")
    }

    private fun setup() {
        activityMainBinding.rvImages.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
    }

    override fun updateUI() {

    }

    override fun handleError(throwable: Throwable?) {
        DLog.e("Data Observer error !--> $throwable")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1000 -> {
                    searchImage(data?.getStringExtra(Global.EXTRA_KEYWORD))
                }
            }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> startActivityForResult<SearchActivity>(1000)
        }
        return super.onOptionsItemSelected(item)
    }
}
