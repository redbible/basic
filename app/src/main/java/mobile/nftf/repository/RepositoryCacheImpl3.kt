package mobile.nftf.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import mobile.nftf.ext.showLongToastSafe
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.RepositoryCache

/*
    디비 이용시 상속하여 변경 가능
 */
class RepositoryCacheImpl3 : RepositoryCache {
    private val liveItems = MutableLiveData<List<Item>>()
    private val items = ArrayList<Item>()

    override fun toggleItem(item: Item) {
        if (items.contains(item)) {
            items.remove(item)
            "removed3".showLongToastSafe()
        } else {
            items.add(item)
            "added3".showLongToastSafe()
        }

        liveItems.postValue(items)
    }

    override fun getItems(): List<Item> {
        return items
    }

    override fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLiveList(): MutableLiveData<List<Item>> {
        return liveItems
    }
}