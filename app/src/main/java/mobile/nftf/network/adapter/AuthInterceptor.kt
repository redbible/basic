package mobile.nftf.network.adapter

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        const val ENABLE_AUTH = "AUTH: true"
    }

    private val userName = "honssyunggyung"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.header("AUTH") == null) {
            return chain.proceed(originalRequest)
        }

        val url = originalRequest.url().toString()
//        Log.d(url)
        return chain.proceed(
            originalRequest.newBuilder()
                .addHeader("X-Cardoc-User", userName)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .method(originalRequest.method(), originalRequest.body())
                .build()
        )
    }
}