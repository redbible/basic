package com.example.basic.ui

import com.example.basic.R
import com.example.basic.databinding.ActivityMainBinding
import com.redbible.baseview.activity.BaseDataBindingActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    val viewModel by inject<MainViewModel>()

    override fun ActivityMainBinding.onBind() {

    }
}