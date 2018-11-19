package com.lezhin.test.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.lezhin.test.utils.NetworkUtils

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel<*>>: AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    private var mViewModel: V?= null

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getViewDataBinding(): T = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        performDataBinding()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if(view != null) {
            val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.let { it.hideSoftInputFromWindow(view.windowToken, 0) }
        }
    }

    fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if(mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()

    }
}
