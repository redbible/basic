package mobile.nftf.repository

import io.reactivex.Single
import mobile.nftf.network.api.ApiTest
import mobile.nftf.network.model.DataTest

class RepositoryTest(private val apiTest: ApiTest) {

    fun refreshList(): Single<List<DataTest>> {
        return Single.just(listOf(DataTest(0, true), DataTest(0, false), DataTest(0, true), DataTest(0, true), DataTest(0, true), DataTest(0, true), DataTest(0, true), DataTest(0, true), DataTest(0, true)))
    }

    fun getName(): Single<String> {
        return apiTest.repoContributors("square", "retrofit")
            .map { it -> it[0].htmlUrl }
    }
}