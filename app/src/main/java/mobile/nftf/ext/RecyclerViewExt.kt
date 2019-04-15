package mobile.nftf.ext

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    if (list == null) {
        return
    }

    when {
        adapter as? BaseRecyclerViewAdapter<Any> != null -> {
            (adapter as BaseRecyclerViewAdapter<Any>).replaceAll(list)
            adapter.notifyDataSetChanged()
        }
    }
}