package mobile.nftf.ui

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter
import mobile.nftf.R
import mobile.nftf.common.ui.recyclerview.BaseViewHolder
import mobile.nftf.model.Content
import mobile.nftf.viewmodel.MainViewModel
import java.util.*

class ItemAdapter(private val mainViewModel: MainViewModel) : BaseRecyclerViewAdapter<Content>() {

    override fun createRecyclableViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Content> {
        return ViewHolder(parent)
    }

    fun onItemMove(from: Int, to: Int): Boolean {
        if (from < to) {
            for (i in from..(to - 1)) {
                Collections.swap(getItems(), i, i + 1)
            }
        } else {
            for (i in from downTo (to + 1)) {
                Collections.swap(getItems(), i, i - 1)
            }
        }
        notifyItemMoved(from, to)

        return true
    }

    inner class ViewHolder(parent: ViewGroup) : BaseViewHolder<Content>(R.layout.todo_item, parent) {
        override fun onDataBind(data: Content) {
            itemView.run {
                findViewById<ImageView>(R.id.checkbox).run {
                    isSelected = data.done
                    setOnClickListener { v ->
                        v.isSelected = !v.isSelected
                        mainViewModel.onCheckChange(data, v.isSelected).subscribe()
                    }
                }
                findViewById<TextView>(R.id.txtDate).text = data.createdAt

                val edit = findViewById<EditText>(R.id.txtContent)
                val editButtion = findViewById<View>(R.id.btnEdit)

                edit.setText(data.content)
                edit.isEnabled = data.isEdited

                editButtion.setOnClickListener {
                    data.isEdited = !data.isEdited
                    edit.isEnabled = data.isEdited
                    if (!data.isEdited) {
                        mainViewModel.updateContent(data, edit.text.toString()).subscribe()
                    }
                }

                findViewById<View>(R.id.btnClose).setOnClickListener { mainViewModel.onDelete(data).subscribe() }
            }
        }
    }
}