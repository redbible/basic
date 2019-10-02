package mobile.nftf.common.ui.recycler

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import mobile.nftf.common.ui.recyclerview.BaseDataBindingViewHolder

class BaseDataBindingRecyclerViewAdapter<T : Any, B : ViewDataBinding>(
    val layoutId: Int,
    private val onDataBindExternal: B.(data: T) -> Unit
) : RecyclerView.Adapter<BaseDataBindingViewHolder<T, B>>() {

    private var items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDataBindingViewHolder<T, B> {
        return ViewHolder(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseDataBindingViewHolder<T, B>, position: Int) {
        holder.bindData(items[position])
    }

    fun setItems(items: MutableList<T>) {
        this.items = items
    }

    fun getItems() = items

    fun getItem(position: Int) = items[position]

    fun add(item: T): Boolean {
        val insertPosition = itemCount

        val result = items.add(item)

        notifyItemInserted(insertPosition)

        return result
    }

    fun add(position: Int, item: T) {
        items.add(position, item)

        notifyItemInserted(position)
    }

    fun addAll(items: Collection<T>): Boolean {
        val insertPosition = itemCount

        val result = this.items.addAll(items)

        notifyItemRangeInserted(insertPosition, items.size)

        return result
    }

    fun addAll(items: Array<T>): Boolean {
        val insertPosition = itemCount

        val result = this.items.addAll(items)

        notifyItemRangeInserted(insertPosition, items.size)

        return result
    }

    fun addAll(position: Int, items: Collection<T>): Boolean {
        val result = this.items.addAll(position, items)

        notifyItemRangeInserted(position, items.size)

        return result
    }

    fun set(position: Int, item: T): T {
        val result = items.set(position, item)

        notifyItemChanged(position)

        return result
    }

    fun removeAt(position: Int): T {
        val result = items.removeAt(position)

        notifyItemRemoved(position)

        return result
    }

    fun remove(item: T): Boolean {
        val position = getItems().indexOf(item)

        if (position == -1) {
            return false
        }

        removeAt(position)

        return true
    }

    fun clear() {
        items.clear()

        notifyDataSetChanged()
    }

    fun replaceAll(items: List<T>) {
        replaceAllSilently(items)
        notifyDataSetChanged()
    }

    fun replaceAllSilently(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
    }

    inner class ViewHolder(parent: ViewGroup) : BaseDataBindingViewHolder<T, B>(layoutId, parent) {
        override fun B.onDataBind(data: T) {
            onDataBindExternal.invoke(binding, data)
        }
    }
}