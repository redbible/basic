package mobile.nftf.ui.mainpage

import coinone.co.kr.official.common.ui.fragment.BaseDataBindingFragment
import com.bumptech.glide.Glide
import mobile.nftf.R
import mobile.nftf.common.ui.recycler.BaseDataBindingRecyclerViewAdapter
import mobile.nftf.databinding.CartFragmentBinding
import mobile.nftf.databinding.ImgItem2Binding
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.RepositoryCache
import mobile.nftf.viewmodel.CartViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment : BaseDataBindingFragment<CartFragmentBinding>(R.layout.cart_fragment) {
    private val viewModel by viewModel<CartViewModel>()
    private val repo by inject<RepositoryCache>()

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun CartFragmentBinding.onBind() {
        vm = viewModel
        viewModel.bindLifecycle(this@CartFragment)

        rv.adapter = BaseDataBindingRecyclerViewAdapter<Item, ImgItem2Binding>(R.layout.img_item2) {
            Glide.with(img)
                .load(it.thumbnail)
                .into(img)

            img.setOnClickListener { _ -> repo.toggleItem(it) }
        }
    }
}