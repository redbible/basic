package mobile.nftf.repository

import io.reactivex.Single
import mobile.nftf.network.api.ApiTest
import java.util.regex.Pattern

class RepositoryTest(private val apiTest: ApiTest) {
    private val pattern = "(http(s?):/)(/[^/]+)+.(?:jpg|gif|png)"

    fun getHttps(): Single<List<String>> {
        return apiTest.getHttps()
            .map { getFilteredImageUrls(it) }
    }

    private fun getFilteredImageUrls(response: String): List<String> {
        val ret = ArrayList<String>()
        Pattern.compile(pattern).matcher(response).run {
            while (this.find()) {
                ret.add(this.group())
            }
        }

        return ret.filter { !it.contains("\n") }.toList()
    }
}