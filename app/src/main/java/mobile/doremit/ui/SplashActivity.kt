package mobile.doremit.ui

import coinone.co.kr.official.common.ui.activity.BaseActivity
import io.reactivex.Observable
import mobile.doremit.ActivityNavigator
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity(0) {

    override fun setContentView() {

    }

    override fun setupView() {
        Observable.just(2)
            .delay(2, TimeUnit.SECONDS)
            .subscribe { ActivityNavigator.with(this).main().start() }
    }
}