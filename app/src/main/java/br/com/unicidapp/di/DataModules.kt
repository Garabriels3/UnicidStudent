package br.com.unicidapp.di

import br.com.data.source.local.SharedPreferencesCache
import br.com.domain.storange.Cache
import org.koin.dsl.module

object DataModules {

    val dataModules = module {
        single<Cache> {
            SharedPreferencesCache(
                get()
            )
        }
    }
}