package br.com.unicidapp.di

import android.graphics.drawable.Drawable
import br.com.domain.boundary.ResourcesDrawable
import br.com.unicidapp.ui.average.AverageViewModel
import br.com.unicidapp.ui.average.addAverage.AddAverageViewModel
import br.com.unicidapp.ui.home.HomeViewModel
import br.com.unicidapp.ui.login.LoginViewModel
import br.com.unicidapp.ui.optionDialog.OptionsViewModel
import br.com.unicidapp.ui.register.RegisterViewModel
import br.com.unicidapp.ui.splash.SplashViewModel
import br.com.unicidapp.utils.resources.ResourcesDrawableImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModules {

    val utilsModules = module {
        single<ResourcesDrawable<Drawable>> { ResourcesDrawableImpl(get()) }
    }

    val viewModelModules = module {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { OptionsViewModel() }
        viewModel { SplashViewModel(get()) }
        viewModel { AverageViewModel(get(), get()) }
        viewModel { AddAverageViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get()) }
    }
}