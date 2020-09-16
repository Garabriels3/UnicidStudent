package br.com.domain.usecase.login

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface LoginUseCase {
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun createAccount(user: User)
}