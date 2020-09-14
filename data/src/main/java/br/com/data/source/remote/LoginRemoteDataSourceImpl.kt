package br.com.data.source.remote

import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuthException

class LoginRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : LoginRemoteDataSource {

    override fun createAccount(email: String, password: String): FirebaseResponse {
        return try {
            firebaseAuth.createAccount(email, password)
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(e.toString())
        }
    }

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
}