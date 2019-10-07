package mobile.nftf.ui

import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.ActivityNavigator
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.ui.mainpage.PageAdapter

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    override fun MainActivityBinding.onBind() {
        vi = this@MainActivity

        viewPager.adapter = PageAdapter(supportFragmentManager)
        tab.setupWithViewPager(viewPager)

//        DialogBasic(this@MainActivity, "testt", "qoweas")
//            .setCancel()
//            .setClickListenerConfirm { "Confirmm".showLongToastSafe() }
//            .show()
//
        ActivityNavigator.with(this@MainActivity).second("qwe").start()
//
//        ActivityNavigator.with(this@MainActivity)
//            .main()
//            .add()
//            .second("qwe")
//            .start()
//
//        Loading.show(this@MainActivity)
//        Observable.just(1)
//            .delay(2, TimeUnit.SECONDS)
//            .subscribe {
//                Loading.show(this@MainActivity)
//            }
    }
}
