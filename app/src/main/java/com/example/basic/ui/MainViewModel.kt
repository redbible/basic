package com.example.basic.ui

import androidx.lifecycle.MutableLiveData
import com.example.basic.repository.RepositoryDummy
import com.redbible.baseview.viewmodel.BaseViewModel

class MainViewModel(
    private val repositoryDummy: RepositoryDummy
) : BaseViewModel() {
    val liveData = MutableLiveData<List<String>>()

    init {
        liveData.postValue(repositoryDummy.getWords())
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }
}