package br.com.domain.repository

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User

interface HomeRepository {
   suspend fun signOut(): FirebaseResponse
   suspend fun getInfo(token: String, userInfo: (User) -> Unit)
   suspend fun getCurrentUser(): FirebaseResponse
}