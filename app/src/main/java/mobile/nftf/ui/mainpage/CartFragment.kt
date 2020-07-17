package mobile.nftf.ui.mainpage

import com.redbible.baseview.fragment.BaseDataBindingFragment
import com.redbible.baseview.recycler.BaseDataBindingRecyclerViewAdapter
import mobile.nftf.R
import mobile.nftf.databinding.CartFragmentBinding
import mobile.nftf.databinding.ImgItem2Binding
import mobile.nftf.model.Item
import mobile.nftf.viewmodel.CartViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment : BaseDataBindingFragment<CartFragmentBinding>(R.layout.cart_fragment) {
    private val viewModel by viewModel<CartViewModel>()

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun CartFragmentBinding.onBind() {
        vm = viewModel
        viewModel.bindLifecycle(this@CartFragment)

        rv.adapter = BaseDataBindingRecyclerViewAdapter<Item>()
            .addViewType(
                BaseDataBindingRecyclerViewAdapter.MultiViewType<Item, ImgItem2Binding>(R.layout.img_item2) {
                    item = it
                    vm = viewModel
                }
            )
//        rv.adapter = BaseDataBindingRecyclerViewAdapter<Item, ImgItem2Binding>(R.layout.img_item2) {
//            item = it
//            vm = viewModel
//
////            with(img) {
////                loadUrl(it.thumbnail)
////                setOnClickListener { _ -> viewModel.onClickImage(it) }
////            }
//        }
    }
}