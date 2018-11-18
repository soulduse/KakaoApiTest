package com.lezhin.test.ui.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lezhin.test.R
import com.lezhin.test.utils.Global
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val url = intent.getStringExtra(Global.EXTRA_DETAIL_DOC_URL)
        initWebView(url)
    }

    private fun initWebView(url: String){
        wv_web_hard
                .setProgressbar(webview_progressbar)
                .setWebPathUrl(url)
                .setBackPressed { backPressed() }
                .initWebView()
    }

    private fun backPressed() {
        if (wv_web_hard.canGoBack()) {
            wv_web_hard.goBack()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        wv_web_hard.destroy()
        super.onDestroy()
    }
}
