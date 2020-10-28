package br.com.data.source.remote.loginDataSource

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface LoginRemoteDataSource {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
    suspend fun getUserInfo(token: String, userInfo: (User) -> Unit)
    fun logoutAccount()
}