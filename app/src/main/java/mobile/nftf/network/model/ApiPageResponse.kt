package coinone.co.kr.official.common.network.api.model

data class ApiPageResponse<T>(
        val meta: PageResponse,
        val documents: List<T>
)