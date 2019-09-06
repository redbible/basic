package mobile.nftf.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Single
import mobile.nftf.common.ui.viewmodel.BaseViewModel
import mobile.nftf.repository.RepositoryTest

class MainViewModel(val repositoryTest: RepositoryTest) : BaseViewModel() {
    val liveImages = MutableLiveData<List<String>>()

    fun fetchUrls(): Completable {
        return repositoryTest.getHttps()
            .map { it }
            .doOnSuccess { it -> liveImages.postValue(it) }
            .toCompletable()
    }
}