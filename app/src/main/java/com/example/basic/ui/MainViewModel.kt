package com.example.basic.ui

import com.redbible.baseview.viewmodel.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun add(a: Int, b: Int): Int {
        return a + b
    }
}