package mobile.nftf.ui.mainpage

import coinone.co.kr.official.common.ui.fragment.BaseDataBindingFragment
import mobile.nftf.R
import mobile.nftf.databinding.SearchFragmentBinding
import mobile.nftf.repository.impl.InterfaceCache
import mobile.nftf.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject

class SearchFragment : BaseDataBindingFragment<SearchFragmentBinding>(R.layout.search_fragment) {
    private val viewModel by inject<SearchViewModel>()
    private val repositoryCache by inject<InterfaceCache>()

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun SearchFragmentBinding.onBind() {
        viewModel.bindLifecycle(this@SearchFragment)
        vm = viewModel
        ItemAdapter(viewModel, repositoryCache).run {
            this.initWith(rv)
            viewModel.setRequestRefresh { this.refresh() }
        }
    }
}