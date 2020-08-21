package br.com.domain.repository

interface LoginRepository {
    fun createAccount(email: String, password: String)
}