package mobile.nftf.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.repository.RepositoryTest

class MainViewModel(val repository: RepositoryTest) : BaseViewModel() {
    val liveHello = MutableLiveData<String>()

    init {

    }

    fun getNameByHtmlUrl(): Single<String> {
        return repository.getName().map { it.split("github.com/")[1] }
    }
}