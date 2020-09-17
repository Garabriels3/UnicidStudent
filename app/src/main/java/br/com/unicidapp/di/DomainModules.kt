package br.com.unicidapp.di

import br.com.domain.usecase.RegisterUseCase.RegisterUseCase
import br.com.domain.usecase.RegisterUseCase.RegisterUseCaseImpl
import br.com.domain.usecase.login.LoginUseCase
import br.com.domain.usecase.login.LoginUseCaseImpl
import org.koin.dsl.module

object DomainModules {

    val domainModules = module {
        single<LoginUseCase> { LoginUseCaseImpl(get()) }
        single<RegisterUseCase> { RegisterUseCaseImpl(get()) }
    }
}