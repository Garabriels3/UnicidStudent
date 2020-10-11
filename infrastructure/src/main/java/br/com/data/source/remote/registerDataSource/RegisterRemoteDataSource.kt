package br.com.data.source.remote.registerDataSource

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User

interface RegisterRemoteDataSource {
    suspend fun createAccount(email: String, password: String): FirebaseResponse
    suspend fun createAccountStore(user: User?): FirebaseResponse
    suspend fun getGrid(get: (List<SelectionItem>) -> Unit)
}