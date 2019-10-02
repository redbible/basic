package coinone.co.kr.official.common.ui.recyclerview

import android.view.View
import android.view.ViewGroup
import mobile.nftf.common.ui.recyclerview.BaseViewHolder

@Deprecated("Do not Need")
class SimpleRecyclerViewAdapter<T : Any>(
        private val layoutId: Int,
        private val onDataBindExternal: (view: View, data: T) -> Unit
) : BaseRecyclerViewAdapter<T>() {

    override fun createRecyclableViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return ViewHolder(parent)
    }

    inner class ViewHolder(parent: ViewGroup) : BaseViewHolder<T>(layoutId, parent) {
        override fun onDataBind(data: T) {
            onDataBindExternal.invoke(itemView, data)
        }
    }
}