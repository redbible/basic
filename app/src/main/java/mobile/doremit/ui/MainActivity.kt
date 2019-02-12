package mobile.doremit.ui

import android.view.View
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import kotlinx.android.synthetic.main.main_activity.*
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

//        viewPager.adapter
    }

    fun onClickBottomButton(view: View, position: Int) {
        view.isSelected = !view.isSelected
    }
}
