package com.example.basic.ui

import androidx.lifecycle.MutableLiveData
import com.example.basic.R
import com.example.basic.databinding.FragmentBottomupBinding
import com.example.basic.databinding.FragmentDialogBinding
import com.redbible.baseview.fragment.BaseDataBindingBottomSheetFragment
import com.redbible.baseview.fragment.BaseDataBindingDialogFragment

class DialogBasic :
    BaseDataBindingDialogFragment<FragmentDialogBinding>(R.layout.fragment_dialog) {
    companion object {
        fun getInstance() = DialogBasic()
    }

    val liveText = MutableLiveData<String>()

    override fun FragmentDialogBinding.onBind() {
        vi = this@DialogBasic

        liveText.postValue("ddas")
    }
}