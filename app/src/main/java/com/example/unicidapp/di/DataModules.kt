package com.example.unicidapp.di

import com.example.data.source.local.SharedPreferencesCache
import com.example.domain.storange.Cache
import org.koin.dsl.module

object DataModules {

    val dataModules = module {
        single<Cache> { SharedPreferencesCache(get()) }
    }
}