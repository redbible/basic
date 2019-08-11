package mobile.nftf.ext

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter
import coinone.co.kr.official.common.ui.recyclerview.SimpleRecyclerViewAdapter
import mobile.nftf.util.Log

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
            Log.d("recycler View Notify1")
        }
        adapter as? SimpleRecyclerViewAdapter<Any> != null -> {
            (adapter as SimpleRecyclerViewAdapter<Any>).replaceAll(list)
            adapter.notifyDataSetChanged()
            Log.d("recycler View Notify2")
        }
    }
}