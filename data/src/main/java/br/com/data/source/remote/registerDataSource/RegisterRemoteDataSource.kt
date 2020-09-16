package br.com.data.source.remote.registerDataSource

import br.com.data.model.firestore.UserData
import br.com.domain.entity.FirebaseResponse

interface RegisterRemoteDataSource {
    fun createAccount(email: String, password: String): FirebaseResponse
    fun createAccount(userData: UserData): FirebaseResponse
}