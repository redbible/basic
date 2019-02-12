package redbible.basic.repository

import io.reactivex.Single
import redbible.basic.network.api.ApiTest

class RepositoryTest(private val apiTest: ApiTest) {

    fun getName(): Single<String> {
        return apiTest.repoContributors("square", "retrofit")
            .map { it -> it[0].htmlUrl }
    }
}