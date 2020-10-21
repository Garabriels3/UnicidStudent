package br.com.domain.usecase.RegisterUseCase

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User

interface RegisterUseCase {
    suspend fun createAccountStore(user: User?)
    suspend fun createAccount(email: String, password: String): FirebaseResponse
    suspend fun getGrid(get: (List<SelectionItem>) -> Unit)
}