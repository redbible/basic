package mobile.nftf.network.api

import coinone.co.kr.official.common.network.api.model.ApiPageResponse
import io.reactivex.Single
import mobile.nftf.network.adapter.AuthInterceptor
import mobile.nftf.network.enumeration.Sort
import mobile.nftf.network.model.ClipKakao
import mobile.nftf.network.model.ImageKakao
import retrofit2.http.*


interface ApiTest {

    @GET("v2/search/image")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun fetchImages(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 30,
        @Query("sort") sort: String = Sort.recency.name
    ): Single<ApiPageResponse<ImageKakao>>

    @GET("v2/search/vclip")
    @Headers(AuthInterceptor.ENABLE_AUTH)
    fun fetchClips(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 30,
        @Query("sort") sort: String = Sort.recency.name
    ): Single<ApiPageResponse<ClipKakao>>
}