package redbible.basic.ui

import coinone.co.kr.official.common.ui.activity.BaseActivity
import redbible.basic.ActivityNavigator

class SplashActivity: BaseActivity(0) {

    override fun setContentView() {

    }

    override fun setupView() {
        ActivityNavigator.with(this).main().start()
    }
}