package mobile.nftf.viewmodel

import io.reactivex.Single
import mobile.nftf.common.ui.recycler.PaginationRecyclerViewAdapter
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.InterfaceCache
import mobile.nftf.util.Disposer
import mobile.nftf.util.disposeOnDestroy

class CartViewModel(private val interfaceCache: InterfaceCache) : BaseViewModel(),
    PaginationRecyclerViewAdapter.DataProvider<Item> {

    private var isEnd = true
    private var requestRefresh: (() -> Unit)? = null

    fun setRequestRefresh(disposer: Disposer, refresh: (() -> Unit)) {
        requestRefresh = refresh
        interfaceCache.onChangedItems {
            isEnd = false
            requestRefresh?.invoke()
        }.disposeOnDestroy(disposer)
    }

    override fun onDestroy() {
        requestRefresh = null
        super.onDestroy()
    }

    override fun isEnd(): Boolean {
        return isEnd
    }

    override fun getLoader(page: Int): Single<List<Item>> {
        return Single.just(interfaceCache.getItems())
            .doOnSuccess { isEnd = true }
    }
}