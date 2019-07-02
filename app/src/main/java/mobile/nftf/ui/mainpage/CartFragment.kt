package mobile.nftf.ui.mainpage

import coinone.co.kr.official.common.ui.activity.BaseActivity
import coinone.co.kr.official.common.ui.fragment.BaseDataBindingFragment
import mobile.nftf.R
import mobile.nftf.databinding.CartFragmentBinding
import mobile.nftf.repository.impl.InterfaceCache
import mobile.nftf.viewmodel.CartViewModel
import org.koin.android.ext.android.inject

class CartFragment : BaseDataBindingFragment<CartFragmentBinding>(R.layout.cart_fragment) {
    private val viewModel by inject<CartViewModel>()
    private val interfaceCache by inject<InterfaceCache>()

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun CartFragmentBinding.onBind() {
        viewModel.bindLifecycle(this@CartFragment)
        ItemAdapter(viewModel, interfaceCache).run {
            this.initWith(rv)
            viewModel.setRequestRefresh(activity as BaseActivity) { this.refresh() }
        }
    }
}