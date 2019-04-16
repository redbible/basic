package mobile.nftf

import mobile.nftf.di.networkModule
import mobile.nftf.di.repositoryModule
import mobile.nftf.di.viewModelModule
import mobile.nftf.network.enumeration.CoursType
import mobile.nftf.repository.RepositoryTest
import mobile.nftf.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : AutoCloseKoinTest() {
    val mainViewModel by inject<MainViewModel>()
    val repositoryTest by inject<RepositoryTest>()

    @Before
    fun initClient() {
        startKoin(listOf(networkModule, repositoryModule, viewModelModule))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(repositoryTest.getTimeCourse(CoursType.ONE).blockingGet().imageUrl, "https://981park-jeju.s3.ap-northeast-2.amazonaws.com/admin/attraction/bom_draw/68.png")
    }
}
