package br.com.domain.usecase.login

import br.com.domain.repository.LoginRepository

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override fun createAccount(email: String, password: String) {
        loginRepository.createAccount(email, password)
    }
}