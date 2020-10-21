package br.com.data.repository

import br.com.data.source.remote.registerDataSource.RegisterRemoteDataSource
import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User
import br.com.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository {

    override suspend fun getGrid(get: (List<SelectionItem>) -> Unit) {
        registerRemoteDataSource.getGrid(get)
    }

    override suspend fun createAccount(email: String, password: String): FirebaseResponse {
        return registerRemoteDataSource.createAccount(email, password)
    }

    override suspend fun createAccountStore(user: User?) {
        registerRemoteDataSource.createAccountStore(user)
    }
}