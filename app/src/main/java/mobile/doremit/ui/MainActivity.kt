package mobile.doremit.ui

import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import org.koin.android.ext.android.inject
import mobile.doremit.R
import mobile.doremit.databinding.MainActivityBinding
import mobile.doremit.viewmodel.MainViewModel

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    val viewModel by inject<MainViewModel>()

    override fun MainActivityBinding.onBind() {
        viewModel.bindLifecycle(this@MainActivity)
        view = this@MainActivity
        vm = viewModel
    }
}
