package br.com.unicidapp

import android.app.Application
import br.com.unicidapp.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class UnicidApplication: KoinComponent, Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@UnicidApplication)
            modules(getAllModules())
        }
    }
}