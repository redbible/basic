package coinone.co.kr.official.common.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import mobile.nftf.common.ui.recyclerview.BaseViewHolder

abstract class BaseRecyclerViewAdapter<T : Any> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var items = mutableListOf<T>()

    abstract fun createRecyclableViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun getItemCount() = items.size

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val viewHolder = createRecyclableViewHolder(parent, viewType)

        viewHolder.onCreated()

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(items[position])
    }

    fun setItems(items: MutableList<T>) {
        this.items = items
    }

    fun getItems() = items

    fun getItem(position: Int) = items[position]

    open fun add(item: T) = items.add(item)

    open fun add(position: Int, item: T) = items.add(position, item)

    open fun addAll(items: Collection<T>) = this.items.addAll(items)

    open fun addAll(items: Array<T>) = this.items.addAll(items)

    open fun addAll(position: Int, items: Collection<T>) = this.items.addAll(position, items)

    open fun set(position: Int, item: T) = items.set(position, item)

    open fun removeAt(position: Int) = items.removeAt(position)

    open fun remove(item: T) = items.remove(item)

    open fun clear() = items.clear()

    open fun replaceAll(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
    }
}