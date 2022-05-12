package com.redbible.baseview.recycler

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

open class BaseDataBindingRecyclerViewAdapter<T : Any>
    : RecyclerView.Adapter<BaseDataBindingViewHolder<T, ViewDataBinding>>() {

    class MultiViewType<T, B>(
        val layoutId: Int,
        val onDataBindExternal: B.(data: T) -> Unit
    )

    private var items = mutableListOf<T>()
    private val bindings = ArrayList<MultiViewType<T, ViewDataBinding>>()
    private var itemViewType: ((item: T, position: Int, isLast: Boolean) -> Int)? = null
    private var diffUtilItemTheSame: ((old: T, new: T) -> Boolean)? = null
    private var diffUtilContentsTheSame: ((old: T, new: T) -> Boolean)? = null
    private var differ: AsyncListDiffer<T>? = null
    private var differCallback: DiffUtil.ItemCallback<T>? = null

    /**
     * @see addViewType Added ViewType index is viewType Id(0...)
     */
    fun setItemViewType(itemViewType: (item: T, position: Int, isLast: Boolean) -> Int): BaseDataBindingRecyclerViewAdapter<T> {
        this.itemViewType = itemViewType
        return this
    }

    /**
     * Call ViewType(Head, Content, Tail) or ViewType(Head, Content)
     * Call addViewType Sequence(Head, Content, Tail)
     */
    fun setItemViewTypeHeadTail(): BaseDataBindingRecyclerViewAdapter<T> {
        this.itemViewType = { item, position, isLast ->
            when {
                position == 0 -> 0
                isLast -> bindings.size - 1
                else -> 1
            }
        }
        return this
    }

    /**
     * @see MultiViewType
     * @param multiViewType ex) MultiViewType<Item, ImgItemBinding>(R.layout.img_item) { }
     * @see itemViewType return value -> ViewType
     **/
    fun addViewType(multiViewType: Any): BaseDataBindingRecyclerViewAdapter<T> {
        bindings.add(multiViewType as MultiViewType<T, ViewDataBinding>)
        return this
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseDataBindingViewHolder<T, ViewDataBinding> {
        return bindings[viewType].let {
            object : BaseDataBindingViewHolder<T, ViewDataBinding>(it.layoutId, parent) {
                override fun ViewDataBinding.onDataBind(data: T) {
                    it.onDataBindExternal.invoke(binding, data)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: BaseDataBindingViewHolder<T, ViewDataBinding>,
        position: Int
    ) {
        holder.bindData(items[position])
    }

    /**
     * getViewType by position,
     * @see setItemViewType define custom viewType condition
     * @see setItemViewTypeHeadTail use just three or two viewType
     * if you use just one view type don't call setItemViewType, setItemViewTypeHeadTail
     */
    override fun getItemViewType(position: Int): Int {
        return itemViewType?.invoke(getItem(position), position, itemCount - 1 == position) ?: 0
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

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val list = getMoveDiffer().currentList.toMutableList()
        val fromItem = list[fromPosition]

        list.removeAt(fromPosition)

        if (toPosition < fromPosition) list.add(toPosition + 1, fromItem)
        else list.add(toPosition - 1, fromItem)

        getMoveDiffer().submitList(list)
    }

    fun getMoveDiffer(): AsyncListDiffer<T> {
        if (differCallback == null) {
            differCallback = object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                    return diffUtilItemTheSame?.invoke(oldItem, newItem) ?: oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return diffUtilContentsTheSame?.invoke(oldItem, newItem) ?: false
                }

            }
        }

        if (differ == null) {
            differ = AsyncListDiffer(this, differCallback!!)
        }

        return differ!!
    }

    fun clear() {
        items.clear()

        notifyDataSetChanged()
    }

    fun replaceAll(items: List<T>) {
        if (diffUtilContentsTheSame == null) {
            replaceAllSilently(items)
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(
                BaseDiffUtil(
                    getItems(),
                    items,
                    diffUtilContentsTheSame!!,
                    diffUtilItemTheSame
                )
            )
            replaceAllSilently(items)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    /**
     * id를 비교 같은 아이템인지 체크
     */
    fun setDiffUtilItemsTheSame(itemTheSame: (old: T, new: T) -> Boolean): BaseDataBindingRecyclerViewAdapter<T> {
        diffUtilItemTheSame = itemTheSame

        return this
    }

    /**
     * 위를 통해 아이디가 같으면 그 내용물의 변화가 있는지 체크
     */
    fun setDiffUtilContentsTheSame(contentsTheSame: (old: T, new: T) -> Boolean): BaseDataBindingRecyclerViewAdapter<T> {
        diffUtilContentsTheSame = contentsTheSame

        return this
    }

    open fun setDragDropItem(
        recyclerView: RecyclerView,
        response: (from: Int, to: Int) -> Unit
    ): BaseDataBindingRecyclerViewAdapter<T> {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                notifyItemMoved(fromPosition, toPosition)
                moveItem(fromPosition, toPosition)

                response.invoke(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        }).attachToRecyclerView(recyclerView)

        return this
    }

    private fun replaceAllSilently(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)

        //set diffUtilContentsTheSame and diffUtilItemTheSame if use Drag & Drop
        if (diffUtilContentsTheSame != null) {
            getMoveDiffer().submitList(items)
        }
    }
}