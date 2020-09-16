package br.com.domain.repository

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface LoginRepository {
    fun loginAccount(email: String, password: String): FirebaseResponse
    fun createAccount(user: User)
}