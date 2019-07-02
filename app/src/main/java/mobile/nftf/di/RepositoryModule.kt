package mobile.nftf.di

import mobile.nftf.repository.RepositoryCache
import mobile.nftf.repository.RepositoryTest
import mobile.nftf.repository.impl.InterfaceCache
import org.koin.dsl.module.module

val repositoryModule = module {
    single { RepositoryTest(get()) }
    single { RepositoryCache() as InterfaceCache }
}