package mobile.nftf.ui

import android.view.View
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.ActivityNavigator
import mobile.nftf.R
import mobile.nftf.databinding.SplashActivityBinding
import mobile.nftf.network.enumeration.CoursType

class SplashActivity : BaseDataBindingActivity<SplashActivityBinding>(R.layout.splash_activity) {
    override fun SplashActivityBinding.onBind() {
        vi = this@SplashActivity
    }

    fun onClick(vi: View) {
        val course = CoursType.values()[vi.tag.toString().toInt()]

        ActivityNavigator.with(this).main(course).start()
    }
}
