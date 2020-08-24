package com.example.basic

import com.example.basic.di.networkModule
import com.example.basic.di.repositoryModule
import com.example.basic.di.viewModelModule
import com.example.basic.ui.MainViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : AutoCloseKoinTest() {
    private val viewModel by inject<MainViewModel>()

    @Before
    fun init() {
        startKoin {
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    @After
    fun close() {
        stopKoin()
    }

    @Test
    fun verifyMainViewModel() {
        assert(viewModel.add(1, 2) == 3)
    }
}