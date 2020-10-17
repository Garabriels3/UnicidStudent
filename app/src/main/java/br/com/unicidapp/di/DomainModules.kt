package br.com.unicidapp.di

import br.com.domain.usecase.RegisterUseCase.RegisterUseCase
import br.com.domain.usecase.RegisterUseCase.RegisterUseCaseImpl
import br.com.domain.usecase.addAverageUseCase.AddAverageUseCase
import br.com.domain.usecase.addAverageUseCase.AddAverageUseCaseImpl
import br.com.domain.usecase.loginUseCase.LoginUseCase
import br.com.domain.usecase.loginUseCase.LoginUseCaseImpl
import org.koin.dsl.module

object DomainModules {

    val domainModules = module {
        single<LoginUseCase> { LoginUseCaseImpl(get()) }
        single<RegisterUseCase> { RegisterUseCaseImpl(get()) }
        single<AddAverageUseCase> { AddAverageUseCaseImpl(get()) }
    }
}