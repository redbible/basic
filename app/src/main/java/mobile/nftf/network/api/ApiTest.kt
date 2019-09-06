package mobile.nftf.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface ApiTest {

    @GET("collection/sasha/")
    fun getHttps(): Single<String>
}