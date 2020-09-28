package br.com.data.source.remote.loginDataSource

import br.com.domain.entity.FirebaseResponse

interface LoginRemoteDataSource {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
    fun logoutAccount()
}