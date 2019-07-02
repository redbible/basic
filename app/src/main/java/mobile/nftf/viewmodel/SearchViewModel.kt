package mobile.nftf.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mobile.nftf.common.ui.recycler.PaginationRecyclerViewAdapter
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.model.Item
import mobile.nftf.repository.RepositoryTest
import java.util.concurrent.TimeUnit

class SearchViewModel(private val repository: RepositoryTest) : BaseViewModel(),
    PaginationRecyclerViewAdapter.DataProvider<Item> {

    val liveKeyword = MutableLiveData<String>()
    private var isEnd = true
    private var debounceDisposable: Disposable? = null
    private var requestRefresh: (() -> Unit)? = null
    private val keywordObserver = Observer<String> {
        debounceDisposable?.dispose()
        if (!it.isNullOrEmpty()) {
            debounceDisposable = Observable.just(1)
                .delay(1000, TimeUnit.MILLISECONDS)
                .doOnNext { isEnd = false }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { requestRefresh?.invoke() }
        }
    }

    init {
        liveKeyword.value = ""
        liveKeyword.observeForever(keywordObserver)
    }

    fun setRequestRefresh(refresh: (() -> Unit)) {
        requestRefresh = refresh
    }

    override fun onDestroy() {
        debounceDisposable?.dispose()
        liveKeyword.removeObserver(keywordObserver)
        requestRefresh = null
        super.onDestroy()
    }

    override fun isEnd(): Boolean {
        return isEnd
    }

    override fun getLoader(page: Int): Single<List<Item>> {
        return repository.getContents(liveKeyword.value!!, page)
            .doOnSuccess { isEnd = it.is_end }
            .map { it.items }
    }
}