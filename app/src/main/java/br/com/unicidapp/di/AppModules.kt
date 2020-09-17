package br.com.unicidapp.di

import br.com.unicidapp.ui.login.LoginViewModel
import br.com.unicidapp.ui.optionDialog.OptionsViewModel
import br.com.unicidapp.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    val viewModelModules = module {
        viewModel { LoginViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { OptionsViewModel() }
    }
}