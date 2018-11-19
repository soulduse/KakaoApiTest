package com.lezhin.test.utils

import android.net.Uri
import com.facebook.drawee.view.SimpleDraweeView
import com.lezhin.test.api.data.Document


class ImageUtil {
    companion object {
        fun loadImage(imageView: SimpleDraweeView, document: Document) {
            val uri = makeUri(document.imageUrl)
            // 썸네일로 불러오고 싶다면 아래를 호출.
            // val uri = makeUri(document.thumbnailUrl)
            imageView.layoutParams.width = SizeUtil.DISPLAY_WIDTH
            imageView.layoutParams.height = getResizedHeight(document.height, document.width, SizeUtil.DISPLAY_WIDTH)

            DLog.e("""------------------------[BindingAdapter]--------------------------
                |- image thumbnailUrl ---> ${document.thumbnailUrl}
                |- image imageUrl ---> ${document.imageUrl}
                |- height ---> ${document.height}
                |- width ---> ${document.width}
                |- device width ---> ${SizeUtil.DISPLAY_WIDTH}
                |- result width ---> ${imageView.layoutParams.width}
                |- result height ---> ${imageView.layoutParams.height}
            """.trimMargin())
            imageView.setImageURI(uri, null)
        }

        fun loadImage(imageView: SimpleDraweeView, url: String) {
            val uri = makeUri(url)
            imageView.setImageURI(uri, null)
        }

        private fun makeUri(url: String): Uri = Uri.parse(url)

        private fun getResizedHeight(height: Int, width: Int, deviceWidth: Int): Int = (deviceWidth.toDouble() / width * height).toInt()
    }
}
