package mobile.nftf.ui

import android.content.Intent
import android.os.Bundle
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.ActivityNavigator.Companion.KEY_DATA
import mobile.nftf.R
import mobile.nftf.databinding.SecondActivityBinding

class SecondActivity : BaseDataBindingActivity<SecondActivityBinding>(R.layout.second_activity) {
    lateinit var data: String

    override fun setupProperties(bundle: Bundle?) {
        bundle?.run {
            data = getString(KEY_DATA)
        }
    }

    override fun SecondActivityBinding.onBind() {

    }
}