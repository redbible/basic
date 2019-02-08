package coinone.co.kr.official.common.network.api.model

open class ApiException(val code: Int, message: String = "", cause: Throwable? = null) : Exception(message, cause)