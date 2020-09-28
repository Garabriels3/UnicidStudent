package br.com.domain.repository

import br.com.domain.entity.FirebaseResponse

interface LoginRepository {
    suspend fun loginAccount(email: String, password: String): FirebaseResponse
}