package mobile.nftf

import mobile.nftf.di.networkModule
import mobile.nftf.di.repositoryModule
import mobile.nftf.di.viewModelModule
import mobile.nftf.repository.RepositoryTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : AutoCloseKoinTest() {
    val repositoryTest by inject<RepositoryTest>()

    @Before
    fun initClient() {
        startKoin(listOf(networkModule, repositoryModule, viewModelModule))
    }

    @Test
    fun getDates() {
        log(repositoryTest.getHttps().blockingGet().first())
    }

    fun log(string: String) {
        System.out.println("hhh" + string)
    }
}
