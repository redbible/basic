package mobile.nftf.repository

import mobile.nftf.model.Content
import mobile.nftf.repository.impl.InterfaceCache

/*
    디비 이용시 상속하여 변경 가능
 */
class RepositoryCache : InterfaceCache {
    var init = false
    var datas = emptyList<Content>()

    override fun isInit(): Boolean {
        return init
    }

    override fun getItems(): List<Content> {
        return datas
    }

    override fun setItems(contents: List<Content>) {
        datas = contents
        init = true
    }
}