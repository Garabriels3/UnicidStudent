package br.com.data.source.remote.loginDataSource

import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuthException

class LoginRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao
) : LoginRemoteDataSource {

    override suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return firebaseAuth.loginAccount(email, password)
    }

    override fun logoutAccount() {
        TODO("Not yet implemented")
    }
}