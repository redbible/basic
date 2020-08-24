package com.example.basic.di

import com.example.basic.repository.RepositoryTest
import com.example.basic.repository.local.RepositoryCached
import com.example.basic.repository.local.RepositoryDevicePreference
import org.koin.dsl.module

val repositoryModule = module {
    single { RepositoryTest(get()) }
    single<RepositoryCached> { RepositoryDevicePreference() }   //not used
}