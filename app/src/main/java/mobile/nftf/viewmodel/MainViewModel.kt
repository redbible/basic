package mobile.nftf.viewmodel

import android.arch.lifecycle.MutableLiveData
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.repository.RepositoryTest

class MainViewModel(repository: RepositoryTest) : BaseViewModel() {
    val liveHello = MutableLiveData<String>()

    init {
        repository.getName().subscribe { it ->
            liveHello.postValue(it)
        }
    }
}