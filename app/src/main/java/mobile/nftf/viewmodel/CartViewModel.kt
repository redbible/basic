package mobile.nftf.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import mobile.nftf.common.ui.recycler.PaginationRecyclerViewAdapter
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.model.Item
import mobile.nftf.repository.RepositoryCacheImpl3
import mobile.nftf.repository.impl.RepositoryCache
import mobile.nftf.util.Disposer
import mobile.nftf.util.Log
import mobile.nftf.util.disposeOnDestroy

class CartViewModel(val repositoryCache: RepositoryCache) : BaseViewModel() {
    var liveItems = MutableLiveData<List<Item>>()

    init {
//        if (repositoryCache is RepositoryCacheImpl3) {
//            liveItems = repositoryCache.getLiveList()
//        } else {
//            repositoryCache.onChangedItems {
//                liveItems.postValue(it)
//            }.disposeOnDestroy(this)
//        }

        repositoryCache.onChangedItems {
            liveItems.postValue(it)
        }.disposeOnDestroy(this)
    }

    fun onClickImage(item: Item) {
        repositoryCache.toggleItem(item)
    }

    fun plus(a: Int, b: Int) = a + b
}