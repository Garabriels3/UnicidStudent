package br.com.domain.usecase.loginUseCase

import br.com.domain.entity.FirebaseResponse
import br.com.domain.repository.LoginRepository

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return loginRepository.loginAccount(email, password)
    }
}