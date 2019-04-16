package mobile.nftf.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import mobile.nftf.ActivityNavigator.Companion.KEY_DATA
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.ext.replaceAll
import mobile.nftf.network.enumeration.CoursType
import mobile.nftf.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    private val viewModel by inject<MainViewModel>()
    private val timeSlotAdapter by lazy { TimeSlotAdapter() }
    lateinit var courseType: CoursType

    override fun setupProperties(bundle: Bundle?) {
        super.setupProperties(bundle)
        courseType = CoursType.values()[bundle?.getInt(KEY_DATA) ?: 0]
    }

    override fun MainActivityBinding.onBind() {
        viewModel.bindLifecycle(this@MainActivity)
        vi = this@MainActivity
        vm = viewModel

        rv.adapter = timeSlotAdapter
        viewModel.initTimeSlot(courseType) { slotSize, items, isMultiSelect ->
            (rv.layoutManager as GridLayoutManager).spanCount = slotSize
            timeSlotAdapter.isMultiSelected = isMultiSelect
            rv.replaceAll(items)
        }
    }

    fun onClickOk() {
        Log.d("hhh", "${timeSlotAdapter.getSeletedItems()}")
    }
}
