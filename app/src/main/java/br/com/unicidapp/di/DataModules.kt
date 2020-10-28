package br.com.unicidapp.di

import br.com.data.di.MapperModules.documentSnapshotToSelectionItemMapper
import br.com.data.di.MapperModules.documentSnapshotToUserMapper
import br.com.data.di.MapperModules.querySnapshotToAddAverageMapper
import br.com.data.di.MapperModules.querySnapshotToSelectionItemMapper
import br.com.data.repository.AddAverageRepositoryImpl
import br.com.data.repository.HomeRepositoryImpl
import br.com.data.repository.LoginRepositoryImpl
import br.com.data.repository.RegisterRepositoryImpl
import br.com.data.source.local.SharedPreferencesCache
import br.com.data.source.remote.addAverageDataSource.AddAverageRemoteDataSource
import br.com.data.source.remote.addAverageDataSource.AddAverageRemoteDataSourceImpl
import br.com.data.source.remote.homeDataSource.HomeRemoteDataSource
import br.com.data.source.remote.homeDataSource.HomeRemoteDataSourceImpl
import br.com.data.source.remote.loginDataSource.LoginRemoteDataSource
import br.com.data.source.remote.loginDataSource.LoginRemoteDataSourceImpl
import br.com.data.source.remote.registerDataSource.RegisterRemoteDataSource
import br.com.data.source.remote.registerDataSource.RegisterRemoteDataSourceImpl
import br.com.data.source.remote.service.firebase.dao.CourseDao
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.repository.AddAverageRepository
import br.com.domain.repository.HomeRepository
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
                get(), get(), get(named(documentSnapshotToUserMapper))
            )
        }

        single<RegisterRepository> {
            RegisterRepositoryImpl(
                get()
            )
        }

        single<AddAverageRepository> {
            AddAverageRepositoryImpl(
                get()
            )
        }

        single<RegisterRemoteDataSource> {
            RegisterRemoteDataSourceImpl(
                get(), get(), get(), get(named(querySnapshotToSelectionItemMapper))
            )
        }

        single<AddAverageRemoteDataSource> {
            AddAverageRemoteDataSourceImpl(
                get(), get(), get(named(documentSnapshotToSelectionItemMapper)), get(
                    named(
                        querySnapshotToAddAverageMapper
                    )
                )
            )
        }

        single<HomeRemoteDataSource> {
            HomeRemoteDataSourceImpl(
                get(), get(), get(named(documentSnapshotToUserMapper))
            )
        }

        single<HomeRepository> {
            HomeRepositoryImpl(
                get()
            )
        }

        single {
            br.com.data.source.remote.service.firebase.FirebaseAuth()
        }

        single { UserDao() }
        single { CourseDao() }
    }
}