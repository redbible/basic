package redbible.basic.ui

import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import org.koin.android.ext.android.inject
import redbible.basic.R
import redbible.basic.databinding.MainActivityBinding
import redbible.basic.viewmodel.MainViewModel

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    val viewModel by inject<MainViewModel>()

    override fun MainActivityBinding.onBind() {
        viewModel.bindLifecycle(this@MainActivity)
        view = this@MainActivity
        vm = viewModel
    }
}
