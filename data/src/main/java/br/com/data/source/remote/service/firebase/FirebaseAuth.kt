package br.com.data.source.remote.service.firebase

import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuth {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun createAccount(email: String, password: String): FirebaseResponse {
        return try {
            val user =
                auth.createUserWithEmailAndPassword(email, password).await().user?.uid
            auth.currentUser?.sendEmailVerification()
            FirebaseResponse(userUid = user)
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(errorMessage = e.message)
        }
    }

    suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user?.uid
            FirebaseResponse(userUid = user)
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(errorMessage = e.message)
        }
    }

    fun logoutAccount(): FirebaseResponse {
        return try {
            auth.signOut()
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(errorMessage = e.message)
        }
    }

    fun getCurrentUser(): FirebaseResponse {
        return if (auth.currentUser != null)
            FirebaseResponse(userUid = auth.currentUser?.uid)
        else
            FirebaseResponse(errorMessage = "Erro ao recuparar uid")
    }
}