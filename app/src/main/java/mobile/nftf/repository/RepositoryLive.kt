package mobile.nftf.repository

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single
import mobile.nftf.model.Content
import mobile.nftf.repository.impl.InterfaceCache

class RepositoryLive(
    private val repositoryCache: InterfaceCache,
    private val repositoryRemote: RepositoryRemote
) {
    private val liveItems = MutableLiveData<List<Content>>()

    init {
        if (repositoryCache.isInit()) {
            liveItems.postValue(repositoryCache.getItems())
        } else {
            fetchedContent()
        }
    }

    fun getItemsLiveData() = liveItems

    fun addTodo(text: String): Single<Content> {
        return repositoryRemote.addTodos(text)
            .doOnSuccess { fetchedContent() }
    }

    fun updateTodo(content: Content, text: String): Single<Content> {
        return repositoryRemote.updateTodo(content.id, text)
            .doOnSuccess { fetchedContent() }
    }

    fun updateTodo(content: Content, done: Boolean): Single<Content> {
        return repositoryRemote.updateTodo(content.id, done)
            .doOnSuccess { fetchedContent() }
    }

    fun deleteTodo(content: Content): Single<List<Content>> {
        return repositoryRemote.deleteTodo(content.id)
            .doOnSuccess { repositoryCache.setItems(it) }
            .doOnSuccess { liveItems.postValue(it) }
    }

    fun updateSeq(content: Content, seq: Int): Single<List<Content>> {
        return repositoryRemote.updateSeq(content.id, seq + 1)
//            .doOnSuccess { repositoryCache.setItems(it) }
//            .doOnSuccess { liveItems.postValue(it) }
    }

    private fun fetchedContent() {
        repositoryRemote.fetchTodos()
            .doOnSuccess { repositoryCache.setItems(it) }
            .doOnSuccess { liveItems.postValue(it) }
            .subscribe()
    }
}