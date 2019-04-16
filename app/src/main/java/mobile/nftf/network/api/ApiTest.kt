package mobile.nftf.network.api

import io.reactivex.Single
import mobile.nftf.network.model.TimeCourse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiTest {

    @POST("admin/time_course/{ydm}/{course}")
    fun fetchTimeCourse(
        @Path("ydm") ydm: String,
        @Path("course") course: Int
    ): Single<TimeCourse>
}