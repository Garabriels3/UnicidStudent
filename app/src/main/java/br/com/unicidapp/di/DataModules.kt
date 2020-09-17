package br.com.unicidapp.di

import br.com.data.di.MapperModules.querySnapshotToSelectionItemMapper
import br.com.data.repository.LoginRepositoryImpl
import br.com.data.repository.RegisterRepositoryImpl
import br.com.data.source.local.SharedPreferencesCache
import br.com.data.source.remote.loginDataSource.LoginRemoteDataSource
import br.com.data.source.remote.loginDataSource.LoginRemoteDataSourceImpl
import br.com.data.source.remote.registerDataSource.RegisterRemoteDataSource
import br.com.data.source.remote.registerDataSource.RegisterRemoteDataSourceImpl
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.repository.LoginRepository
import br.com.domain.repository.RegisterRepository
import br.com.domain.storange.Cache
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModules {

    val dataModules = module {
        single<Cache> {
            SharedPreferencesCache(
                get()
            )
        }

        single<LoginRepository> {
            LoginRepositoryImpl(
                get()
            )
        }

        single<LoginRemoteDataSource> {
            LoginRemoteDataSourceImpl(
                get(), get()
            )
        }

        single<RegisterRepository> {
            RegisterRepositoryImpl(
                get()
            )
        }

        single<RegisterRemoteDataSource> {
            RegisterRemoteDataSourceImpl(
                get(), get(), get(named(querySnapshotToSelectionItemMapper))
            )
        }

        single {
            br.com.data.source.remote.service.firebase.FirebaseAuth()
        }

        single { UserDao() }
    }
}