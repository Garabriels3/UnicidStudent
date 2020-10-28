package br.com.unicidapp.di

import br.com.data.di.MapperModules.mapperModules
import br.com.unicidapp.di.AppModules.utilsModules
import br.com.unicidapp.di.AppModules.viewModelModules
import br.com.unicidapp.di.DataModules.dataModules
import br.com.unicidapp.di.DomainModules.domainModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(
            *getAppModules(),
            *getDataModules(),
            *getDomainModules()
        )

    private fun getAppModules(): Array<Module> {
        return arrayOf(utilsModules, viewModelModules)
    }

    private fun getDataModules(): Array<Module> {
        return arrayOf(dataModules, mapperModules)
    }

    private fun getDomainModules(): Array<Module> {
        return arrayOf(domainModules)
    }
}