package mobile.nftf.di

import mobile.nftf.viewmodel.CartViewModel
import mobile.nftf.viewmodel.SearchViewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    single { SearchViewModel(get()) }
    single { CartViewModel(get()) }
}