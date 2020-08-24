package com.example.basic.network.model

data class ApiPageResponse<T>(
        val meta: PageResponse,
        val documents: List<T>
)