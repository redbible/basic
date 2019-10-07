package mobile.nftf.di

import mobile.nftf.repository.RepositoryCacheImpl2
import mobile.nftf.repository.RepositoryTest
import mobile.nftf.repository.impl.RepositoryCache
import org.koin.dsl.module

val repositoryModule = module {
    single { RepositoryTest(get()) }
//    single<RepositoryCache> { RepositoryCacheImpl() }
    single<RepositoryCache> { RepositoryCacheImpl2() }
//    single<RepositoryCache> { RepositoryCacheImpl3() }
}