package redbible.basic.viewmodel

import android.arch.lifecycle.MutableLiveData
import redbible.basic.common.ui.viewmodel.BaseViewModel
import redbible.basic.repository.RepositoryTest

class MainViewModel(repository: RepositoryTest) : BaseViewModel() {
    val liveHello = MutableLiveData<String>()

    init {
        repository.getName().subscribe { it ->
            liveHello.postValue(it)
        }
    }
}