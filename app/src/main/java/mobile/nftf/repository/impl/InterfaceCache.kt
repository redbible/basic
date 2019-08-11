package mobile.nftf.repository.impl

import mobile.nftf.model.Content

interface InterfaceCache {
    fun isInit(): Boolean

    fun getItems(): List<Content>

    fun setItems(contents: List<Content>)
}