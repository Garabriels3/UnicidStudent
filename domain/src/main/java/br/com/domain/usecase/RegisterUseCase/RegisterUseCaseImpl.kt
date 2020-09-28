package br.com.domain.usecase.RegisterUseCase

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User
import br.com.domain.repository.RegisterRepository

class RegisterUseCaseImpl(
    private val registerRepository: RegisterRepository
) : RegisterUseCase {

    override suspend fun createAccountStore(user: User?) {
        registerRepository.createAccountStore(user)
    }

    override suspend fun createAccount(email: String, password: String): FirebaseResponse {
        return registerRepository.createAccount(email, password)
    }

    override suspend fun getGrid(get: (List<SelectionItem>) -> Unit) {
        registerRepository.getGrid(get)
    }
}