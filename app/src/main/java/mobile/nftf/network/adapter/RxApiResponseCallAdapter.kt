package coinone.co.kr.official.common.network.api.adapter

import coinone.co.kr.official.common.network.api.model.ApiPageResponse
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter

open class RxApiResponseCallAdapter<R>(private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Any> {

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<R>): Any {
        val result = wrapped.adapt(call)

        if (result as? Observable<ApiPageResponse<R>> != null) {
            return result
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .retryWhen { rxRetryWhen(it.toFlowable(BackpressureStrategy.BUFFER)).toObservable() }
        }
        if (result as? Single<ApiPageResponse<R>> != null) {
            return result
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .retryWhen(this::rxRetryWhen)
        }

        return result
    }

    override fun responseType() = wrapped.responseType()!!

    open fun rxRetryWhen(flowable: Flowable<Throwable>): Flowable<*> {
        return flowable.flatMap { Flowable.error<Throwable>(it) }
    }
}