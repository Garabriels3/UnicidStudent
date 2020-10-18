package br.com.domain.usecase.loginUseCase

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface LoginUseCase {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
    suspend fun getUserInfo(token: String, userInfo: (User) -> Unit)
}