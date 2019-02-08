package coinone.co.kr.official.common.network.api.model

data class ApiResponse<T>(
        val errorCode: Int,
        val errorMsg: String,
        val data: T
)