package com.example.basic.network.adapter

import com.example.basic.network.model.ApiPageResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter

open class RxApiResponseCallAdapter<R>(private val wrapped: CallAdapter<R, *>) :
    CallAdapter<R, Any> {

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<R>): Any {
        val result = wrapped.adapt(call)

        if (result as? Observable<ApiPageResponse<R>> != null) {
            return result
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
//                .doOnNext(this::rxDoOnNext)
        }
        if (result as? Single<ApiPageResponse<R>> != null) {
            return result
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
//                .doOnNext(this::rxDoOnNext)
        }

        return result
    }

//    fun rxDoOnNext(response: ApiResponse<*>) {
//        when (response.errorCode) {
//            0 -> return
//            301, 303 -> throw ExpiredTokenException(getString(com.example.basic.R.string.InvalidAccessTokenException))
//            302, 304 -> throw ExpiredRefreshTokenException(getString(com.example.basic.R.string.TokenExpiredException))
//            else -> throw ApiException(response.errorCode, getErrorMessage(response.errorCode, response.errorMsg))
//        }
//    }

    override fun responseType() = wrapped.responseType()!!
}