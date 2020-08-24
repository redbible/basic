package com.example.basic.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadUrl")
fun ImageView.loadUrl(url: String?) {
    if (url.isNullOrBlank()) {
        return
    }
    Glide.with(this)
        .load(url)
        .into(this)
}