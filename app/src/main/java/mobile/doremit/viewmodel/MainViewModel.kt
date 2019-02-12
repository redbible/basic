package mobile.doremit.viewmodel

import android.arch.lifecycle.MutableLiveData
import mobile.doremit.common.ui.viewmodel.BaseViewModel
import mobile.doremit.repository.RepositoryTest

class MainViewModel(repository: RepositoryTest) : BaseViewModel() {
    val liveHello = MutableLiveData<String>()

    init {
        repository.getName().subscribe { it ->
            liveHello.postValue(it)
        }
    }
}