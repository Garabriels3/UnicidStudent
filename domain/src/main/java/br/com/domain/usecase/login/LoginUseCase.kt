package br.com.domain.usecase.login

interface LoginUseCase {
    fun createAccount(email: String, password: String)
}