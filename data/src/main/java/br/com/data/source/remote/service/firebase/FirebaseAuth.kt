package br.com.data.source.remote.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import br.com.domain.entity.FirebaseResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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

    fun loginAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Login feito com sucesso")
            } else {
                Log.w(TAG, "Falha no login!", task.exception)
            }
        }
    }
}