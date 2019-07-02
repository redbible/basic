package mobile.nftf.common.ui.recycler

import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter

abstract class BaseRecyclerViewAutoUpdateAdapter<T : Any> : BaseRecyclerViewAdapter<T>() {

    override fun add(item: T): Boolean {
        val insertPosition = itemCount

        val result = super.add(item)

        notifyItemInserted(insertPosition)

        return result
    }

    override fun add(position: Int, item: T) {
        super.add(position, item)

        notifyItemInserted(position)
    }

    override fun addAll(items: Collection<T>): Boolean {
        val insertPosition = itemCount

        val result = super.addAll(items)

        notifyItemRangeInserted(insertPosition, items.size)

        return result
    }

    override fun addAll(items: Array<T>): Boolean {
        val insertPosition = itemCount

        val result = super.addAll(items)

        notifyItemRangeInserted(insertPosition, items.size)

        return result
    }

    override fun addAll(position: Int, items: Collection<T>): Boolean {
        val result = super.addAll(position, items)

        notifyItemRangeInserted(position, items.size)

        return result
    }

    override fun set(position: Int, item: T): T {
        val result = super.set(position, item)

        notifyItemChanged(position)

        return result
    }

    override fun removeAt(position: Int): T {
        val result = super.removeAt(position)

        notifyItemRemoved(position)

        return result
    }

    override fun remove(item: T): Boolean {
        val position = getItems().indexOf(item)

        if (position == -1) {
            return false
        }

        removeAt(position)

        return true
    }

    override fun clear() {
        super.clear()

        notifyDataSetChanged()
    }

    override fun replaceAll(items: List<T>) {
        super.replaceAll(items)

        notifyDataSetChanged()
    }

    fun replaceAllSilently(items: List<T>) {
        super.replaceAll(items)
    }
}