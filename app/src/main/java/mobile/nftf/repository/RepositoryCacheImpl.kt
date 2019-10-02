package mobile.nftf.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import mobile.nftf.ext.showLongToastSafe
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.RepositoryCache

/*
    디비 이용시 상속하여 변경 가능
 */
class RepositoryCacheImpl : RepositoryCache {
    private val subject = BehaviorSubject.create<ArrayList<Item>>().apply { onNext(ArrayList()) }

    override fun toggleItem(item: Item) {
        val items = subject.value
        if (items.contains(item)) {
            items.remove(item)
            "removed".showLongToastSafe()
        } else {
            items.add(item)
            "added".showLongToastSafe()
        }
        subject.onNext(items)
    }

    override fun getItems(): List<Item> {
        return subject.value
    }

    override fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable {
        return subject.subscribe { response.invoke(it) }
    }

    override fun getLiveList(): MutableLiveData<List<Item>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}