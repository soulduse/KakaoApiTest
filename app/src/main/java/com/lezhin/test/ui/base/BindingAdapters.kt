package com.lezhin.test.ui.base

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.lezhin.test.api.data.Document
import com.lezhin.test.utils.ImageUtil

@BindingAdapter("document")
fun SimpleDraweeView.loadImage(document: Document) {
    ImageUtil.loadImage(this, document)
}