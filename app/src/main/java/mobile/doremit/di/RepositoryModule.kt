package mobile.doremit.di

import org.koin.dsl.module.module
import mobile.doremit.repository.RepositoryTest

val repositoryModule = module {
    single { RepositoryTest(get()) }
}