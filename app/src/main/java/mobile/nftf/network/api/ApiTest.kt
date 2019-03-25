package mobile.nftf.network.api

import io.reactivex.Single
import mobile.nftf.network.model.Contributor
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiTest {

    @GET("repos/{owner}/{repo}/contributors")
    fun repoContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Single<List<Contributor>>

}