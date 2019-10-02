package mobile.nftf

import mobile.nftf.di.networkModule
import mobile.nftf.di.repositoryModule
import mobile.nftf.di.viewModelModule
import mobile.nftf.repository.RepositoryTest
import mobile.nftf.viewmodel.CartViewModel
import mobile.nftf.viewmodel.SearchViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : AutoCloseKoinTest() {
    private val repositoryTest by inject<RepositoryTest>()

    @Before
    fun initClient() {
        startKoin {
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    @After
    fun close() {
        stopKoin()
    }

    @Test
    fun getDates() {
        var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA)
        val data = "2019-06-21T11:59:12.000+09:00"
//        val data = "2019-06-29T08:05:00"
        log(format.parse(data).toString())
    }

    @Test
    fun getContents() {
//        log("" + repositoryTest.getContents("설현", 1).blockingGet().items.size)
        assertEquals(repositoryTest.getContents("설현", 1).blockingGet().items.first().thumbnail, "")
    }

    @Test
    fun getImages() {
        log(repositoryTest.getImages("설현", 1).blockingGet().documents.first().datetime.toString())
        assertEquals(repositoryTest.getImages("설현", 1).blockingGet().documents.first().thumbnail_url, "")
    }

    @Test
    fun getClips() {
        log(repositoryTest.getClips("설현", 1).blockingGet().documents.first().thumbnail)
        assertEquals(repositoryTest.getClips("설현", 1).blockingGet().documents.first().thumbnail, "")
    }

    @Test
    fun veiwModel() {
        get<CartViewModel>().run {
            assert(this.plus(2, 3) == 5)
        }
    }

    fun log(string: String) {
        System.out.println("hhh" + string)
    }
}
