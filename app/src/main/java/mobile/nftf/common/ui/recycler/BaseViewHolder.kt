package mobile.nftf.common.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Deprecated("Do not Need")
abstract class BaseViewHolder<T : Any> : RecyclerView.ViewHolder {

    private lateinit var data: T

    constructor(layoutId: Int, parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

    constructor(view: View) : super(view)

    protected abstract fun onDataBind(data: T)

    open fun onCreated() {
        setupView(itemView)
    }

    fun bindData(data: T) {
        this.data = data

        onDataBind(data)
    }

    fun getData() = data

    protected open fun setupView(itemView: View) {}
}