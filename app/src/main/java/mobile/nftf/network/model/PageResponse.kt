package coinone.co.kr.official.common.network.api.model

data class PageResponse(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)