package com.lezhin.test.ui.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class TouchWebView : WebView {

    private lateinit var mProgressBar: ProgressBar

    private var webPathUrl: String?= null

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        requestDisallowInterceptTouchEvent(true)
        return super.onTouchEvent(event)
    }

    fun setProgressbar(mProgressBar: ProgressBar): TouchWebView {
        this.mProgressBar = mProgressBar
        return this
    }

    fun setWebPathUrl(webPathUrl: String?): TouchWebView {
        this.webPathUrl = webPathUrl
        return this
    }

    fun setBackPressed(backPressedListener: () -> Unit): TouchWebView {
        setOnKeyListener(OnKeyListener { _, keyCode, keyEvent ->
            when {
                keyEvent.action != KeyEvent.ACTION_DOWN -> return@OnKeyListener true

                keyCode == KeyEvent.KEYCODE_BACK -> {
                    backPressedListener()
                    return@OnKeyListener true
                }

                else -> return@OnKeyListener false
            }
        })

        return this
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK

        isFocusable = true
        isFocusableInTouchMode = true

        webViewClient = CustomWebViewClient(mProgressBar)
        webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                mProgressBar.progress = newProgress
            }
        }

        loadUrl(webPathUrl)
    }

    class CustomWebViewClient(private val progressBar: ProgressBar): WebViewClient(){

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }

}
