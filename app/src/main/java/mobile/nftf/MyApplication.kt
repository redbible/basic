package mobile.nftf

import android.app.Application
import coinone.co.kr.official.network.RxNetworkErrorAdapter
import io.reactivex.plugins.RxJavaPlugins
import mobile.nftf.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
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
                checkNotNull(instance) { "this application does not inherit CoinoneApplication" }
                return instance as MyApplication
            }

        fun getString(stringId: Int): String = globalApplicationContext.getString(stringId)

    }
}