package br.com.data.repository

import br.com.data.source.remote.LoginRemoteDataSource
import br.com.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun createAccount(email: String, password: String) {
        loginRemoteDataSource.createAccount(email, password)
    }
}