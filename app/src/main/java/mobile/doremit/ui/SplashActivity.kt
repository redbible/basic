package mobile.doremit.ui

import coinone.co.kr.official.common.ui.activity.BaseActivity
import io.reactivex.Observable
import mobile.doremit.ActivityNavigator
import mobile.doremit.BuildConfig
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity(0) {

    override fun setContentView() {

    }

    override fun setupView() {
        if (BuildConfig.DEBUG) {
            ActivityNavigator.with(this).main().start()
        } else {
            Observable.just(0)
                .delay(2, TimeUnit.SECONDS)
                .subscribe { ActivityNavigator.with(this).main().start() }
        }

        finish()
    }
}