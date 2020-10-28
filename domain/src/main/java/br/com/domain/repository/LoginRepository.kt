package br.com.domain.repository

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface LoginRepository {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
    suspend fun getUserInfo(token: String, userInfo: (User) -> Unit)
}