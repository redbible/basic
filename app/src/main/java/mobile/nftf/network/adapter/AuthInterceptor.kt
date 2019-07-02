package mobile.nftf.network.adapter

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        const val ENABLE_AUTH = "AUTH: true"
    }

    private val kakaoKey = "d7536dfe20bda97e12adf17c7d0dbab2"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.header("AUTH") == null) {
            return chain.proceed(originalRequest)
        }

        val url = originalRequest.url().toString()
//        Log.d(url)
        return chain.proceed(
            originalRequest.newBuilder()
                .addHeader("Authorization", "KakaoAK $kakaoKey")
                .url(url)
                .method(originalRequest.method(), originalRequest.body())
                .build()
        )
    }
}