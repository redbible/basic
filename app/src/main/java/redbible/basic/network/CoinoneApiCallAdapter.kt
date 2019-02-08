package redbible.basic.network

import coinone.co.kr.official.common.network.api.adapter.RxApiResponseCallAdapter
import coinone.co.kr.official.common.network.api.model.ApiException
import coinone.co.kr.official.common.network.api.model.ApiResponse
import io.reactivex.Flowable
import retrofit2.CallAdapter

class CoinoneApiCallAdapter<R>(wrapped: CallAdapter<R, *>) : RxApiResponseCallAdapter<R>(wrapped) {

    override fun rxDoOnNext(response: ApiResponse<*>) {
        when (response.errorCode) {
            0 -> return
//            301, 303 -> throw ExpiredTokenException(getString(coinone.co.kr.official.R.string.InvalidAccessTokenException))
//            302, 304 -> throw ExpiredRefreshTokenException(getString(coinone.co.kr.official.R.string.TokenExpiredException))
            else -> throw ApiException(response.errorCode, getErrorMessage(response.errorCode, response.errorMsg))
        }
    }

    override fun rxRetryWhen(flowable: Flowable<Throwable>): Flowable<*> {
        return flowable.flatMap {
            //            if (it is ExpiredTokenException) {
//                return@flatMap get(AuthRepository::class.java).refreshToken().toFlowable()
//            }

            return@flatMap Flowable.error<Throwable>(it)
        }
    }

    private fun getErrorMessage(code: Int, defValue: String): String = ""
//            ErrorMessageMapper.getErrorMessage(code).let {
//                if (it.isBlank()) {
//                    defValue
//                } else {
//                    it
//                }
//            }
}