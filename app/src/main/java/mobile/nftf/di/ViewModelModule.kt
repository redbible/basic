package mobile.nftf.di

import mobile.nftf.viewmodel.MainViewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}