package mobile.nftf.di

import mobile.nftf.repository.RepositoryTest
import org.koin.dsl.module.module

val repositoryModule = module {
    single { RepositoryTest(get()) }
}