package br.com.domain.repository

import br.com.domain.entity.FirebaseResponse

interface LoginRepository {
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun createAccount(email: String, password: String): FirebaseResponse
}