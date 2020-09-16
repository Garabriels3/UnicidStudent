package br.com.data.source.remote.loginDataSource

import br.com.domain.entity.FirebaseResponse
import  br.com.domain.entity.User

interface LoginRemoteDataSource {
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun logoutAccount()
    fun createAccount(user: User)
}