package com.example.unicidapp.di

import com.example.unicidapp.di.AppModules.viewModelModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getAppModules())

    private fun getAppModules(): Array<Module> {
        return arrayOf(viewModelModules)
    }
}