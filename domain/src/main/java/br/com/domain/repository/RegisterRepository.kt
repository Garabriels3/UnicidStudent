package br.com.domain.repository

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User

interface RegisterRepository {
    suspend fun getGrid(get: (List<SelectionItem>) -> Unit)
    suspend fun createAccount(email: String, password: String): FirebaseResponse
    suspend fun createAccountStore(user: User?)
}