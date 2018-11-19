package com.lezhin.test.utils

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Utility class for resizing
 */
object SizeUtil {
    var DISPLAY_WIDTH: Int = 0
    var DISPLAY_HEIGHT: Int = 0

    fun initSizeData(activity: Activity) {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        DISPLAY_WIDTH = metrics.widthPixels
        DISPLAY_HEIGHT = metrics.heightPixels
    }
}
