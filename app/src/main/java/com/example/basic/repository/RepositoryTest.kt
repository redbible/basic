package com.example.basic.repository

import com.example.basic.network.model.ApiPageResponse
import com.example.basic.network.api.ApiTest
import com.example.basic.network.model.ClipKakao
import com.example.basic.network.model.ImageKakao
import io.reactivex.Single

class RepositoryTest(private val apiTest: ApiTest) {
    private val defaultPageSize = 30
    var currentPage = 1
    var isEndClips = false
    var isEndImages = false

    fun getImages(keyword: String, page: Int): Single<ApiPageResponse<ImageKakao>> {
        return apiTest.fetchImages(keyword, page, defaultPageSize)
            .doOnSuccess { isEndImages = it.meta.is_end }
    }

    fun getClips(keyword: String, page: Int): Single<ApiPageResponse<ClipKakao>> {
        return apiTest.fetchClips(keyword, page, defaultPageSize)
            .doOnSuccess { isEndClips = it.meta.is_end }
    }
}