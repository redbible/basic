package com.example.basic.ui

import androidx.lifecycle.MutableLiveData
import com.example.basic.R
import com.example.basic.databinding.FragmentBasicBinding
import com.redbible.baseview.fragment.BaseDataBindingFragment

class FragmentBasic :
    BaseDataBindingFragment<FragmentBasicBinding>(R.layout.fragment_basic) {
    companion object {
        fun getInstance() = FragmentBasic()
    }

    val liveText = MutableLiveData<String>()

    override fun FragmentBasicBinding.onBind() {
        vi = this@FragmentBasic

        liveText.postValue("ddas")
    }
}