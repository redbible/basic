package com.example.basic.ui

import com.example.basic.ActivityNavigator
import com.example.basic.R
import com.example.basic.databinding.ActivityMainBinding
import com.example.basic.databinding.ListMainBinding
import com.redbible.baseview.activity.BaseDataBindingActivity
import com.redbible.baseview.recycler.BaseDataBindingRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    enum class ButtonType {
        BOTTOM_UP, DIALOG, FRAGMENT
    }

    private val viewModel by viewModel<MainViewModel>()

    override fun ActivityMainBinding.onBind() {
        vi = this@MainActivity
        vm = viewModel

        rv.adapter = BaseDataBindingRecyclerViewAdapter<String>()
            .setItemViewType { item, position, isLast -> 0 }        //You can set by viewType by item, position, isLastItem
            .addViewType(   //ItemViewType 0
                BaseDataBindingRecyclerViewAdapter.MultiViewType<String, ListMainBinding>(
                    R.layout.list_main
                ) {
                    item = it
                }
            )
        //can addViewType, will be ItemViewType 1
    }

    fun onClickButton(type: ButtonType) {
        when (type) {
            ButtonType.BOTTOM_UP -> BottomUpBasic.getInstance().show(supportFragmentManager)
            ButtonType.DIALOG -> DialogBasic.getInstance().show(supportFragmentManager)
            ButtonType.FRAGMENT -> ActivityNavigator.with(this).second().start()
        }
    }
}