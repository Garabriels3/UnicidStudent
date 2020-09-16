package br.com.data.source.remote.service.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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