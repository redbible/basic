package mobile.nftf

import mobile.nftf.di.networkModule
import mobile.nftf.di.repositoryModule
import mobile.nftf.di.viewModelModule
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

    @Before
    fun initClient() {
        startKoin(listOf(networkModule, repositoryModule, viewModelModule))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(mainViewModel.getNameByHtmlUrl().blockingGet(), "JakeWharton")
    }
}
