package mobile.nftf.di

import org.koin.dsl.module.module
import mobile.nftf.viewmodel.MainViewModel

val viewModelModule = module {
    single { MainViewModel(get()) }
}