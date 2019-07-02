package mobile.nftf.repository.impl

import io.reactivex.disposables.Disposable
import mobile.nftf.model.Item

interface InterfaceCache {

    fun toggleItem(item: Item)

    fun getItems(): List<Item>

    fun onChangedItems(response: (items: List<Item>) -> Unit): Disposable
}