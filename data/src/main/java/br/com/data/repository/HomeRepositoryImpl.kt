package br.com.data.repository

import br.com.data.source.remote.homeDataSource.HomeRemoteDataSource
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User
import br.com.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun signOut(): FirebaseResponse {
        return homeRemoteDataSource.signOut()
    }

    override suspend fun getInfo(token: String, userInfo: (User) -> Unit) {
        homeRemoteDataSource.getInfo(token, userInfo)
    }

    override suspend fun getCurrentUser(): FirebaseResponse {
        return homeRemoteDataSource.getCurrentUser()
    }
}