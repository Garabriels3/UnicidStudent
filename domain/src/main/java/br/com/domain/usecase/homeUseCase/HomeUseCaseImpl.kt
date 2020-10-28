package br.com.domain.usecase.homeUseCase

import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User
import br.com.domain.repository.HomeRepository
import br.com.domain.storange.Cache

class HomeUseCaseImpl(
    private val homeRepository: HomeRepository
) : HomeUseCase {

    override suspend fun signOut(): FirebaseResponse {
        return homeRepository.signOut()
    }

    override suspend fun getInfo(token: String, userInfo: (User) -> Unit) {
        homeRepository.getInfo(token, userInfo)
    }

    override suspend fun getCurrentUser(): FirebaseResponse {
        return homeRepository.getCurrentUser()
    }
}