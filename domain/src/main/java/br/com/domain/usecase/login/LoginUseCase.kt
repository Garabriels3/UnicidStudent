package br.com.domain.usecase.login

import br.com.domain.entity.FirebaseResponse

interface LoginUseCase {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
}