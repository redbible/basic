package mobile.nftf.ui

import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.ui.mainpage.PageAdapter

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    override fun MainActivityBinding.onBind() {
        vi = this@MainActivity

        viewPager.adapter = PageAdapter(supportFragmentManager)
        tab.setupWithViewPager(viewPager)
    }
}
