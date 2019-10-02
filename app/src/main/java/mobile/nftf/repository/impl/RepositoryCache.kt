package mobile.nftf.repository.impl

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import mobile.nftf.model.Item

interface RepositoryCache {

    fun toggleItem(item: Item)

    fun getItems(): List<Item>

    fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable

    fun getLiveList(): MutableLiveData<List<Item>>
}