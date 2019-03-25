package mobile.nftf.repository

import io.reactivex.Single
import mobile.nftf.network.api.ApiTest

class RepositoryTest(private val apiTest: ApiTest) {

    fun getName(): Single<String> {
        return apiTest.repoContributors("square", "retrofit")
            .map { it -> it[0].htmlUrl }
    }
}