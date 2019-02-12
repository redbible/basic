package redbible.basic.di

import org.koin.dsl.module.module
import redbible.basic.viewmodel.MainViewModel

val viewModelModule = module {
    single { MainViewModel(get()) }
}