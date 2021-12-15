package com.example.basic.ui

import androidx.lifecycle.MutableLiveData
import com.example.basic.R
import com.example.basic.databinding.FragmentBottomupBinding
import com.redbible.baseview.fragment.BaseDataBindingBottomSheetFragment

class BottomUpBasic :
    BaseDataBindingBottomSheetFragment<FragmentBottomupBinding>(R.layout.fragment_bottomup) {
    companion object {
        fun getInstance() = BottomUpBasic()
    }

    val liveText = MutableLiveData<String>()

    override fun FragmentBottomupBinding.onBind() {
        vi = this@BottomUpBasic

        liveText.postValue("ddas")
    }
}