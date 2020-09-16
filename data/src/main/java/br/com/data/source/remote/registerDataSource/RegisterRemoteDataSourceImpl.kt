package br.com.data.source.remote.registerDataSource

import br.com.data.model.firestore.UserData
import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuthException

class RegisterRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : RegisterRemoteDataSource {

    override fun createAccount(email: String, password: String): FirebaseResponse {
        return try {
            firebaseAuth.createAccount(email, password)
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(e.toString())
        }
    }

    override fun createAccount(userData: UserData): FirebaseResponse {
        TODO("Not yet implemented")
    }
}