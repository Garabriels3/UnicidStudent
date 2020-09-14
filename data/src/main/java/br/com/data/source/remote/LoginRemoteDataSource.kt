package br.com.data.source.remote

import br.com.domain.entity.FirebaseResponse

interface LoginRemoteDataSource {
    fun createAccount(email: String, password: String): FirebaseResponse
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun logoutAccount()
}