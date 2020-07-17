package mobile.nftf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.redbible.baseview.disposeOnDestroy
import com.redbible.baseview.viewmodel.BaseViewModel
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.RepositoryCache

class CartViewModel(val repositoryCache: RepositoryCache) :
    BaseViewModel() {
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