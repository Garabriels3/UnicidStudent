package br.com.domain.usecase.login

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User
import br.com.domain.repository.LoginRepository

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override fun loginAccount(email: String, password: String): FirebaseResponse {
        return loginRepository.loginAccount(email, password)
    }

    override fun createAccount(user: User) {
        loginRepository.createAccount(user)
    }
}