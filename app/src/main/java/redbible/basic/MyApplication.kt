package redbible.basic

import android.app.Application
import coinone.co.kr.official.network.RxNetworkErrorAdapter
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.android.startKoin
import redbible.basic.di.*

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(networkModule, repositoryModule, viewModelModule))

        RxJavaPlugins.setErrorHandler { RxNetworkErrorAdapter().processErrorHandler(it) }
        RxJavaPlugins.lockdown()

        instance = this

    }

    companion object {
        private var instance: MyApplication? = null
        val globalApplicationContext: MyApplication
            get() {
                if (instance == null)
                    throw IllegalStateException("this application does not inherit CoinoneApplication")
                return instance as MyApplication
            }

        fun getString(stringId: Int): String = globalApplicationContext.getString(stringId)

    }
}