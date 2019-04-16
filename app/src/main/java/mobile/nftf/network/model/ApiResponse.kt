package coinone.co.kr.official.common.network.api.model

data class ApiResponse<T>(
        val code: String,
        val message: String,
        val data: T
)