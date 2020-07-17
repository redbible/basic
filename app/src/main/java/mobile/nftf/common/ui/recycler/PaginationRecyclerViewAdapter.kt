package mobile.nftf.common.ui.recycler

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.CompletableSubject

@Deprecated("Do not Need")
abstract class PaginationRecyclerViewAdapter<T : Any>(
    private val provider: DataProvider<T>
) : BaseRecyclerViewAutoUpdateAdapter<T>() {

    private var currentPage = 0

    fun initWith(
        recyclerView: RecyclerView,
        loadMoreThreshold: Int = 3
    ) {
        recyclerView.adapter = this
        recyclerView.addOnScrollListener(ScrollListener(loadMoreThreshold))
        refresh().subscribe()
    }

    fun refresh(): Completable {
        currentPage = 0

        clear()
        return loadMore()
    }

    fun loadMore(): Completable {
        if (!canLoadMore()) {
            return Completable.error(IllegalStateException("더이상 불러올 정보가 없습니다."))
        }

        currentPage++

        val completableEmitter = CompletableSubject.create()

        provider.getLoader(currentPage)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { addAll(it) }
            .observeOn(Schedulers.computation())
            .toCompletable()
            .subscribe(completableEmitter::onComplete, completableEmitter::onError)

        return completableEmitter
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
    }

    fun canLoadMore() = !provider.isEnd()

    private fun autoFirstLoad(recyclerView: RecyclerView) {
        if (canLoadMore() && !recyclerView.canScrollVertically(1)) {
            loadMore().subscribe { autoFirstLoad(recyclerView) }
        }
    }

    interface DataProvider<T> {
        fun isEnd(): Boolean

        fun getLoader(page: Int): Single<List<T>>
    }

    class ScrollListener(private val loadMoreThreshold: Int = 3) : RecyclerView.OnScrollListener() {

        private var isLoading = false

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val adapter = recyclerView.adapter as? PaginationRecyclerViewAdapter<*>
                ?: throw IllegalStateException("Unsupported RecyclerView Adapter. implement PaginationRecyclerViewAdapter")

            val layoutManager = recyclerView.layoutManager
            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                else -> throw IllegalStateException("Unsupported LayoutManager")
            }

            if (isLoading || !adapter.canLoadMore()) {
                return
            }

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - loadMoreThreshold) {
                isLoading = true
                adapter.loadMore()
                    .doOnTerminate { isLoading = false }
                    .subscribe()
            }
        }
    }
}