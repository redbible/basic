package mobile.nftf.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    if (list == null) {
        return
    }

    when {
        adapter as? BaseRecyclerViewAdapter<Any> != null -> {
            (adapter as BaseRecyclerViewAdapter<Any>).run {
                replaceAll(list)
                notifyDataSetChanged()
            }
        }
    }
}