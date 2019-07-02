package mobile.nftf.repository

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import mobile.nftf.ext.showLongToastSafe
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.InterfaceCache
import java.lang.ref.WeakReference

/*
    디비 이용시 상속하여 변경 가능
 */
class RepositoryCache : InterfaceCache {
    private val behaviorSubject = BehaviorSubject.create<ArrayList<Item>>().apply { onNext(ArrayList()) }

    override fun toggleItem(item: Item) {
        val items = behaviorSubject.value
        if (items.contains(item)) {
            items.remove(item)
            "removed".showLongToastSafe()
        } else {
            items.add(item)
            "added".showLongToastSafe()
        }
        behaviorSubject.onNext(items)
    }

    override fun getItems(): List<Item> {
        return behaviorSubject.value
    }

    override fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable {
        return behaviorSubject.subscribe { response.invoke(it) }
    }
}