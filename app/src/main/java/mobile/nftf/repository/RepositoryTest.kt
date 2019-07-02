package mobile.nftf.repository

import coinone.co.kr.official.common.network.api.model.ApiPageResponse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import mobile.nftf.model.Contents
import mobile.nftf.model.Item
import mobile.nftf.network.api.ApiTest
import mobile.nftf.network.model.ClipKakao
import mobile.nftf.network.model.ImageKakao

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

    fun getContents(keyword: String, page: Int = 1): Single<Contents> {
        if (currentPage >= page) {
            isEndClips = false
            isEndImages = false
        }
        currentPage = page

        val imgs = getImages(keyword, page).map { it.documents }
        val clips = getClips(keyword, page).map { it.documents }

        return when {
            //둘다 불러올게 있는 경우
            !isEndImages && !isEndClips -> Single.zip(imgs, clips,
                BiFunction<List<ImageKakao>, List<ClipKakao>, Contents> { t1, t2 ->
                    val imageItems = t1.map { toItem(it) }
                    val clipItems = t2.map { toItem(it) }

                    Contents(isEndClips && isEndImages, page, imageItems.plus(clipItems))
                })
            //비디오만 남은 경우
            !isEndClips -> clips.map { Contents(isEndClips, page, it.map { toItem(it) }) }
            //이미지만 남은 경우
            !isEndImages -> imgs.map { Contents(isEndImages, page, it.map { toItem(it) }) }
            //빈 데이터 배열 반납
            else -> Single.just(Contents(true, page, emptyList()))
        }.doOnSuccess { it.items = it.items.sortedByDescending { it.dateMilliSec } }
    }

    private fun toItem(clip: ClipKakao) = Item(clip.thumbnail, clip.datetime.time, clip = clip)

    private fun toItem(image: ImageKakao) = Item(image.thumbnail_url, image.datetime.time, image = image)
}