package mobile.nftf.ui

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter
import mobile.nftf.R
import mobile.nftf.common.ui.recyclerview.BaseViewHolder
import mobile.nftf.model.TimeSlot

class TimeSlotAdapter : BaseRecyclerViewAdapter<TimeSlot>() {
    enum class ViewType {
        TIME,
        SLOT
    }

    var isMultiSelected = false

    override fun createRecyclableViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TimeSlot> {
        return when (ViewType.values()[viewType]) {
            ViewType.TIME -> ViewHolderTime(parent)
            ViewType.SLOT -> ViewHolderSlot(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    fun getSeletedItems() = getItems().filter { it.isSelected }

    inner class ViewHolderTime(parent: ViewGroup) : BaseViewHolder<TimeSlot>(R.layout.time_item, parent) {
        lateinit var txtTime: TextView

        override fun onDataBind(data: TimeSlot) {
            txtTime.text = data.time
        }

        override fun setupView(itemView: View) {
            super.setupView(itemView)
            txtTime = itemView.findViewById(R.id.txtTime)
        }
    }

    inner class ViewHolderSlot(parent: ViewGroup) : BaseViewHolder<TimeSlot>(R.layout.slot_item, parent) {
        lateinit var checkBox: CheckBox
        lateinit var txtState: TextView
        lateinit var layoutMain: View

        override fun onDataBind(data: TimeSlot) {
            layoutMain.isEnabled = data.isEnabled
            checkBox.run {
                isChecked = data.isSelected
                isEnabled = data.isEnabled
                text = data.name
            }
            txtState.text = if (data.isEnabled) "보통" else "매진"

            checkBox.setOnClickListener {
                if (!isMultiSelected) {
                    getItems().forEach { it.isSelected = false }
                }
                data.isSelected = !data.isSelected
                checkBox.isChecked = data.isSelected
                it.post { notifyDataSetChanged() }
            }
        }

        override fun setupView(itemView: View) {
            super.setupView(itemView)
            itemView.run {
                checkBox = findViewById(R.id.checkbox)
                txtState = findViewById(R.id.txtState)
                layoutMain = findViewById(R.id.layoutMain)
            }
        }
    }
}