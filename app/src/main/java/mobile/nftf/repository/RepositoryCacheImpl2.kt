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
class RepositoryCacheImpl2 : RepositoryCache {
    private val items = ArrayList<Item>()
    private val subject = PublishSubject.create<ArrayList<Item>>()

    override fun toggleItem(item: Item) {
        if (items.contains(item)) {
            items.remove(item)
            "removed2".showLongToastSafe()
        } else {
            items.add(item)
            "added2".showLongToastSafe()
        }

        subject.onNext(items)
    }

    override fun getItems(): List<Item> {
        return items
    }

    override fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable {
        return subject.subscribe { response.invoke(it) }
    }

    override fun getLiveList(): MutableLiveData<List<Item>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}