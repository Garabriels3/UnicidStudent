package br.com.data.source.remote.homeDataSource

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface HomeRemoteDataSource {
    suspend fun signOut(): FirebaseResponse
    suspend fun getInfo(token: String, userInfo: (User) -> Unit)
    suspend fun getCurrentUser(): FirebaseResponse
}