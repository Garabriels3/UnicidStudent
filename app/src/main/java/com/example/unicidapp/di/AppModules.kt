package com.example.unicidapp.di

import com.example.unicidapp.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    val viewModelModules = module {
        viewModel { LoginViewModel() }
    }
}