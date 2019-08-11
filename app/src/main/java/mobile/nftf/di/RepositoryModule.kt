package mobile.nftf.di

import mobile.nftf.repository.RepositoryCache
import mobile.nftf.repository.RepositoryRemote
import mobile.nftf.repository.RepositoryLive
import mobile.nftf.repository.impl.InterfaceCache
import org.koin.dsl.module.module

val repositoryModule = module {
    single { RepositoryRemote(get()) }
    single { RepositoryCache() as InterfaceCache }
    single { RepositoryLive(get(), get()) }
}