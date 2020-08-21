package br.com.unicidapp.di

import br.com.domain.usecase.login.LoginUseCase
import br.com.domain.usecase.login.LoginUseCaseImpl
import org.koin.dsl.module

object DomainModules {

    val domainModules = module {
        single<LoginUseCase> { LoginUseCaseImpl(get()) }
    }
}