package com.example.basic

import android.app.Application
import com.example.basic.di.networkModule
import com.example.basic.di.repositoryModule
import com.example.basic.di.viewModelModule
import com.example.basic.network.adapter.RxNetworkErrorAdapter
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

        RxJavaPlugins.setErrorHandler { RxNetworkErrorAdapter().processErrorHandler(it) }
        RxJavaPlugins.lockdown()

        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        val globalApplicationContext: MyApplication
            get() {
                checkNotNull(instance) { "this application does not inherit Application" }
                return instance as MyApplication
            }

        fun getString(stringId: Int): String = globalApplicationContext.getString(stringId)
    }
}