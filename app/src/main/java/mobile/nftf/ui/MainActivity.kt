package mobile.nftf.ui

import android.view.View
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import coinone.co.kr.official.common.ui.recyclerview.SimpleRecyclerViewAdapter
import org.koin.android.ext.android.inject
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.network.model.DataTest
import mobile.nftf.viewmodel.MainViewModel

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    val viewModel by inject<MainViewModel>()

    override fun MainActivityBinding.onBind() {
        viewModel.bindLifecycle(this@MainActivity)
        view = this@MainActivity
        vm = viewModel

        rv.adapter = SimpleRecyclerViewAdapter<DataTest>(R.layout.test_item) { view, data ->

        }
//        viewPager.adapter
    }

    fun onClickBottomButton(view: View, position: Int) {
        view.isSelected = !view.isSelected
    }
}
