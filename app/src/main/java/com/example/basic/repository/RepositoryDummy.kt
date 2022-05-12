package com.example.basic.repository

import com.example.basic.network.model.MainData

class RepositoryDummy {
    fun getWords() = listOf(MainData("qwe", 0), MainData("asd", 1), MainData("zxc", 2), MainData("sdf", 3), MainData("xcv", 4), MainData("wer", 5))
}