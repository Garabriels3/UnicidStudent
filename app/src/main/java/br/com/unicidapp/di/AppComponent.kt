package br.com.unicidapp.di

import br.com.unicidapp.di.AppModules.viewModelModules
import br.com.unicidapp.di.DataModules.dataModules
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