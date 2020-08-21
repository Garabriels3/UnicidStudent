package br.com.data.source.remote.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuth {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Criacao de usuario feita com sucesso")
            } else {
                Log.w(TAG, "Falha na criacao de usuario!", task.exception)
            }
        }
    }
}