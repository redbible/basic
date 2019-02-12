package mobile.doremit.repository

import io.reactivex.Single
import mobile.doremit.network.api.ApiTest

class RepositoryTest(private val apiTest: ApiTest) {

    fun getName(): Single<String> {
        return apiTest.repoContributors("square", "retrofit")
            .map { it -> it[0].htmlUrl }
    }
}