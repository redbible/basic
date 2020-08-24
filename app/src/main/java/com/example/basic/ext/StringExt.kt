package com.example.basic.ext

import android.widget.Toast
import com.example.basic.MyApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

fun String.showLongToastSafe() {
    Single.just(this)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { _ ->
            Toast.makeText(
                MyApplication.globalApplicationContext,
                this,
                Toast.LENGTH_LONG
            ).show()
        }
}