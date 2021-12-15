package com.example.basic.util

import android.util.Log
import com.example.basic.BuildConfig

object Log {
    fun d(log: String) {
        if (BuildConfig.DEBUG)
            Log.d("hhh", log)
    }
}