package redbible.basic.di

import org.koin.dsl.module.module
import redbible.basic.repository.RepositoryTest

val repositoryModule = module {
    single { RepositoryTest(get()) }
}