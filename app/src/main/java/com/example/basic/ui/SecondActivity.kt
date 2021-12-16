package com.example.basic.ui

import com.example.basic.R
import com.example.basic.databinding.ActivitySecondBinding
import com.redbible.baseview.activity.BaseDataBindingActivity

class SecondActivity : BaseDataBindingActivity<ActivitySecondBinding>(R.layout.activity_second) {
    override fun ActivitySecondBinding.onBind() {
        vi = this@SecondActivity

        FragmentBasic.getInstance().commitAllowingStateLoss(supportFragmentManager, R.id.fragment)

        showKeyboard(edit)
    }
}