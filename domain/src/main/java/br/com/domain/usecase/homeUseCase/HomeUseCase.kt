package br.com.domain.usecase.homeUseCase

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface HomeUseCase {
    suspend fun signOut(): FirebaseResponse
    suspend fun getInfo(token: String, userInfo: (User) -> Unit)
    suspend fun getCurrentUser(): FirebaseResponse
}