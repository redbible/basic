package com.example.basic.ui

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.basic.ActivityNavigator
import com.example.basic.R
import com.example.basic.databinding.ActivityMainBinding
import com.example.basic.databinding.ListMainBinding
import com.example.basic.network.model.MainData
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

        rv.adapter = BaseDataBindingRecyclerViewAdapter<MainData>()
            .setItemViewType { item, position, isLast -> 0 }        //You can set by viewType by item, position, isLastItem
            .addViewType(   //ItemViewType 0
                BaseDataBindingRecyclerViewAdapter.MultiViewType<MainData, ListMainBinding>(
                    R.layout.list_main
                ) {
                    item = it
                }
            ).setDiffUtilContentsTheSame { old, new -> old == new }
            .setDragDropItem(rv) { from, to ->
                val items = viewModel.liveData.value!!

                val fromItem = items.first { it.index == from }
                val toItem = items.first { it.index == to }

                val temp = fromItem.index
                fromItem.index = toItem.index
                toItem.index = temp

                Log.d("hhq", "from: ${fromItem.string} ${fromItem.index}, to: ${toItem.string} ${toItem.index}")
            }

        //can addViewType, will be ItemViewType 1

//        disabledBackPressedToast()
//        disabledSliderFinish()
//        disabledPortrait()
    }

    fun onClickButton(type: ButtonType) {
        when (type) {
            ButtonType.BOTTOM_UP -> BottomUpBasic.getInstance().show(supportFragmentManager)
            ButtonType.DIALOG -> DialogBasic.getInstance().show(supportFragmentManager)
            ButtonType.FRAGMENT -> ActivityNavigator.with(this).second().start()
        }
    }
}