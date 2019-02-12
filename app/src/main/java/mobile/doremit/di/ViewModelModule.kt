package mobile.doremit.di

import org.koin.dsl.module.module
import mobile.doremit.viewmodel.MainViewModel

val viewModelModule = module {
    single { MainViewModel(get()) }
}