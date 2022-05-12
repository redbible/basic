package com.example.basic.ui

import androidx.lifecycle.MutableLiveData
import com.example.basic.network.model.MainData
import com.example.basic.repository.RepositoryDummy
import com.redbible.baseview.viewmodel.BaseViewModel

class MainViewModel(
    private val repositoryDummy: RepositoryDummy
) : BaseViewModel() {
    val liveData = MutableLiveData<List<MainData>>()

    init {
        liveData.postValue(repositoryDummy.getWords())
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }
}