package br.com.data.repository

import br.com.data.source.remote.LoginRemoteDataSource
import br.com.domain.entity.FirebaseResponse
import br.com.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun loginAccount(email: String, password: String): FirebaseResponse {
        return loginRemoteDataSource.loginAccount(email, password)
    }

    override fun createAccount(email: String, password: String): FirebaseResponse {
        return loginRemoteDataSource.createAccount(email, password)
    }
}