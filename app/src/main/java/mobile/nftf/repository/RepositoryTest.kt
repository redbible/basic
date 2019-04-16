package mobile.nftf.repository

import io.reactivex.Single
import mobile.nftf.network.api.ApiTest
import mobile.nftf.network.enumeration.CoursType
import mobile.nftf.network.model.TimeCourse
import java.text.SimpleDateFormat
import java.util.*

class RepositoryTest(private val apiTest: ApiTest) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

    fun getTimeCourse(courseType: CoursType, date: Date = Date()): Single<TimeCourse> {
        return apiTest.fetchTimeCourse(dateFormat.format(date), courseType.ordinal)
    }
}