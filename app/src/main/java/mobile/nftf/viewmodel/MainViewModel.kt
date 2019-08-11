package mobile.nftf.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.enumeration.OrderType
import mobile.nftf.model.Content
import mobile.nftf.repository.RepositoryLive
import java.util.concurrent.TimeUnit

class MainViewModel(private val repositoryLive: RepositoryLive) : BaseViewModel() {
    val liveEdit = MutableLiveData<String>()
    val liveItems: MutableLiveData<List<Content>> = repositoryLive.getItemsLiveData()
    var disposableDebounceSeqCall: Disposable? = null
    var targetPosition = 0

    fun addItems() {
        if (!liveEdit.value.isNullOrEmpty()) {
            repositoryLive.addTodo(liveEdit.value!!)
                .subscribe()
        }
    }

    fun onCheckChange(content: Content, done: Boolean) =
        repositoryLive.updateTodo(content, done)

    fun updateContent(contents: Content, content: String) =
        repositoryLive.updateTodo(contents, content)

    fun onDelete(content: Content) =
        repositoryLive.deleteTodo(content)

    fun sorting(orderType: OrderType) {
        liveItems.value?.run {
            when (orderType) {
                OrderType.DEFAULT -> liveItems.postValue(this.sortedBy { it.seq })
                OrderType.NAME -> liveItems.postValue(this.sortedBy { it.content })
                OrderType.TIME -> liveItems.postValue(this.sortedBy { it.createdAt })
            }
        }
    }

    fun moveSeq(to: Int) {
        liveItems.value?.run {
            disposableDebounceSeqCall?.dispose()
            disposableDebounceSeqCall = repositoryLive.updateSeq(get(targetPosition), to)
                .delay(1, TimeUnit.SECONDS)
                .subscribe()
        }
    }
}