package br.com.data.source.remote.service.firebase

import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class FirebaseAuth {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun createAccount(email: String, password: String): FirebaseResponse {
        return try {
            val user =
                auth.createUserWithEmailAndPassword(email, password).await().user?.uid
            FirebaseResponse(userUid = user)
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(errorMessage = e.toString())
        }
    }

    suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user?.uid
            FirebaseResponse(userUid = user)
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(errorMessage = e.toString())
        }
    }
}