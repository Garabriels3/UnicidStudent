package br.com.domain.usecase.login

import br.com.domain.entity.FirebaseResponse

interface LoginUseCase {
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun createAccount(email: String, password: String): FirebaseResponse
}