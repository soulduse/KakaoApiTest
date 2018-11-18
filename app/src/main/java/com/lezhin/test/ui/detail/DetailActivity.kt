package com.lezhin.test.ui.detail

import android.os.Bundle
import com.lezhin.test.BR
import com.lezhin.test.R
import com.lezhin.test.databinding.ActivityDetailBinding
import com.lezhin.test.ui.base.BaseActivity
import com.lezhin.test.ui.webview.WebViewActivity
import com.lezhin.test.utils.Global
import com.lezhin.test.utils.ImageUtil
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity: BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailNavigation {
    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun getBindingVariable(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel = detailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.setNavigator(this)
        activityDetailBinding = getViewDataBinding()
        showImage()
    }

    private fun showImage() {
        val imageUrl: String? = intent.getStringExtra(Global.EXTRA_DETAIL_PHOTO)
        if (imageUrl.isNullOrEmpty()) {
            toast(R.string.error_image)
            return
        }

        ImageUtil.loadImage(activityDetailBinding.myImageView, imageUrl!!)
    }

    override fun goToLink() {
        val docUrl: String? = intent.getStringExtra(Global.EXTRA_DETAIL_DOC_URL)
        startActivity<WebViewActivity>(Global.EXTRA_DETAIL_DOC_URL to docUrl)
    }

    override fun toolbarCancel() {
        finish()
    }
}
