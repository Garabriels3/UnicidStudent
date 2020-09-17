package br.com.data.repository

import br.com.data.source.remote.loginDataSource.LoginRemoteDataSource
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User
import br.com.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun loginAccount(email: String, password: String): FirebaseResponse {
        return loginRemoteDataSource.loginAccount(email, password)
    }
}