package mobile.nftf.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.model.TimeSlot
import mobile.nftf.network.enumeration.CoursType
import mobile.nftf.network.model.TimeCourse
import mobile.nftf.repository.RepositoryTest
import mobile.nftf.ui.TimeSlotAdapter
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(private val repository: RepositoryTest) : BaseViewModel() {
    val liveDescription = MutableLiveData<String>()

    fun initTimeSlot(
        courseType: CoursType,
        response: (slotSize: Int, items: List<TimeSlot>, isMultiSelect: Boolean) -> Unit
    ) {
        repository.getTimeCourse(courseType)
            .doOnSuccess { liveDescription.postValue(it.timeDesc.replace("\\n", "\n")) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it -> response.invoke(it.slotStocks.size + 1, getSortedItems(it), it.multipleSelect) }
    }

    fun getTodayString() = DateFormat.getDateInstance(DateFormat.FULL).format(Date())

    private fun getSortedItems(timeCourse: TimeCourse): List<TimeSlot> {
        val timeSlots = ArrayList<TimeSlot>()
        timeSlots.addAll(timeCourse.slotStocks.first().timeSlotStocks.map {
            TimeSlot(
                TimeSlotAdapter.ViewType.TIME,
                it.time
            )
        })

        timeCourse.slotStocks.sortedBy { it.orderNo }.forEach { slot ->
            timeSlots.addAll(slot.timeSlotStocks.map {
                TimeSlot(
                    TimeSlotAdapter.ViewType.SLOT,
                    it.time,
                    slot.extId,
                    slot.extProductId,
                    it.extTimeId,
                    slot.slotName,
                    it.stockStatus == "NORMAL"
                )
            })
        }

        Log.d("hhh", "size ${timeSlots.size}")
        return timeSlots.sortedWith(kotlin.Comparator { a, b ->
            if (a.time == b.time) (if (a.viewType == TimeSlotAdapter.ViewType.TIME) 1 else 0) else b.time.compareTo(a.time)
        })
    }
}