package mobile.nftf.di

import mobile.nftf.viewmodel.CartViewModel
import mobile.nftf.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { CartViewModel(get()) }
}