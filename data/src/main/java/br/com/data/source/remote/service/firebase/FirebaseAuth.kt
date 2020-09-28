package br.com.data.source.remote.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import br.com.domain.entity.FirebaseResponse
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseAuth {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun createAccount(email: String, password: String): FirebaseResponse {
        val user: String?
        user =
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Criacao de usuario feita com sucesso")
                } else {
                    Log.w(TAG, "Falha na criacao de usuario!", task.exception)
                    FirebaseResponse(task.exception.toString())
                }

            }.await().user?.uid
        return FirebaseResponse(userUid = user)
    }

    suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user?.uid
            FirebaseResponse(userUid = user)
        } catch (e: Exception) {
            FirebaseResponse(errorMessage = e.toString())
        }
    }
}