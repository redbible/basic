package mobile.nftf

import mobile.nftf.di.networkModule
import mobile.nftf.di.repositoryModule
import mobile.nftf.di.viewModelModule
import mobile.nftf.repository.RepositoryRemote
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
    val repositoryTest by inject<RepositoryRemote>()

    @Before
    fun initClient() {
        startKoin(listOf(networkModule, repositoryModule, viewModelModule))
    }

    @Test
    fun getDates() {
        log(repositoryTest.fetchTodos().blockingGet().first().updatedAt.toString())
    }

    @Test
    fun addData() {
        log(repositoryTest.addTodos("testtqwe7").blockingGet().toString())
    }

    @Test
    fun update() {
        log(repositoryTest.updateTodo(3, false).blockingGet().toString())
    }

    @Test
    fun delete() {
        repositoryTest.deleteTodo(1).blockingGet()
    }

    @Test
    fun updateSeq() {
        repositoryTest.updateSeq(2, 2).blockingGet()
    }

    fun log(string: String) {
        System.out.println("hhh" + string)
    }
}
