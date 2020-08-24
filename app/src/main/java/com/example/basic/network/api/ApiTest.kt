package com.example.basic.network.api

import com.example.basic.network.model.ApiPageResponse
import com.example.basic.network.adapter.AuthInterceptor
import com.example.basic.network.enumeration.Sort
import com.example.basic.network.model.ClipKakao
import com.example.basic.network.model.ImageKakao
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


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