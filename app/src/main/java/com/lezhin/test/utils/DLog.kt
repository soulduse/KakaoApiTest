package com.lezhin.test.utils

import android.util.Log
import com.lezhin.test.MyApplication

object DLog {
    private const val TAG: String = "Dave"
    fun e(message: String) {
        if (MyApplication.DEBUG) Log.e(TAG, buildLogMsg(message))
    }

    fun w(message: String) {
        if (MyApplication.DEBUG) Log.w(TAG, buildLogMsg(message))
    }

    fun i(message: String) {
        if (MyApplication.DEBUG) Log.i(TAG, buildLogMsg(message))
    }

    fun d(message: String) {
        if (MyApplication.DEBUG) Log.d(TAG, buildLogMsg(message))
    }

    fun v(message: String) {
        if (MyApplication.DEBUG) Log.v(TAG, buildLogMsg(message))
    }

    private fun buildLogMsg(message: String): String {
        val ste: StackTraceElement? = Thread.currentThread().stackTrace[4]
        return StringBuilder().apply {
            append("[")
            append(ste?.fileName?.replace(".java", ""))
            append("::")
            append(ste?.methodName)
            append("]")
            append(message)
        }.toString()
    }
}
