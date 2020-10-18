package br.com.unicidapp.di

import br.com.unicidapp.ui.average.AverageViewModel
import br.com.unicidapp.ui.average.addAverage.AddAverageViewModel
import br.com.unicidapp.ui.login.LoginViewModel
import br.com.unicidapp.ui.optionDialog.OptionsViewModel
import br.com.unicidapp.ui.register.RegisterViewModel
import br.com.unicidapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    val viewModelModules = module {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { OptionsViewModel() }
        viewModel { SplashViewModel(get()) }
        viewModel { AverageViewModel() }
        viewModel { AddAverageViewModel(get(), get()) }
    }
}