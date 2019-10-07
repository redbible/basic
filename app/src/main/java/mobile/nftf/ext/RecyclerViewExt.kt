package mobile.nftf.ext

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import coinone.co.kr.official.common.ui.recyclerview.BaseRecyclerViewAdapter
import mobile.nftf.common.ui.recycler.BaseDataBindingRecyclerViewAdapter
import mobile.nftf.common.ui.recycler.BaseMulitDataBindingRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    if (list == null) {
        return
    }

    when {
        adapter as? BaseDataBindingRecyclerViewAdapter<Any, ViewDataBinding> != null -> {
            (adapter as BaseDataBindingRecyclerViewAdapter<Any, ViewDataBinding>).run {
                replaceAll(list)
            }
        }
        adapter as? BaseMulitDataBindingRecyclerViewAdapter<Any> != null -> {
            (adapter as BaseMulitDataBindingRecyclerViewAdapter<Any>).run {
                replaceAll(list)
            }
        }
        adapter as? BaseRecyclerViewAdapter<Any> != null -> {
            (adapter as BaseRecyclerViewAdapter<Any>).run {
                replaceAll(list)
                notifyDataSetChanged()
            }
        }
    }
}