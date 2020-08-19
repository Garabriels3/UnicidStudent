package com.example.unicidapp.di

import com.example.unicidapp.di.AppModules.viewModelModules
import com.example.unicidapp.di.DataModules.dataModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(
            *getAppModules(),
            *getDataModules()
        )

    private fun getAppModules(): Array<Module> {
        return arrayOf(viewModelModules)
    }

    private fun getDataModules(): Array<Module> {
        return arrayOf(dataModules)
    }
}