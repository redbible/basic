package mobile.nftf.ui

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.enumeration.OrderType
import mobile.nftf.viewmodel.MainViewModel
import org.koin.android.ext.android.inject


class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    private val mainViewModel by inject<MainViewModel>()

    override fun MainActivityBinding.onBind() {
        vi = this@MainActivity
        vm = mainViewModel

        val adapter = ItemAdapter(mainViewModel)
        val touchHelper = ListItemTouchHelper(adapter, mainViewModel)

        ItemTouchHelper(touchHelper).attachToRecyclerView(rv)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                mainViewModel.moveSeq(toPosition)
            }
        })

        rv.adapter = adapter
    }

    fun onClickAddItem() {
        mainViewModel.addItems()
    }

    fun onClickOrder(type: OrderType) {
        mainViewModel.sorting(type)
    }
}
