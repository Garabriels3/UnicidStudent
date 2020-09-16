package br.com.data.source.remote.loginDataSource

import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuthException

class LoginRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao
) : LoginRemoteDataSource {

    override fun loginAccount(email: String, password: String): FirebaseResponse {
        return try {
            firebaseAuth.loginAccount(email, password)
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(e.toString())
        }
    }

    override fun logoutAccount() {
        TODO("Not yet implemented")
    }

    override fun createAccount(user: br.com.domain.entity.User) {
        userDao.createAccountStore(user)
    }
}