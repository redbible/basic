package mobile.nftf.di

import org.koin.dsl.module.module
import mobile.nftf.repository.RepositoryTest

val repositoryModule = module {
    single { RepositoryTest(get()) }
}