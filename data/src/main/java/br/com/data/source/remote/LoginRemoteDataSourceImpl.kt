package br.com.data.source.remote

import br.com.data.source.remote.service.firebase.FirebaseAuth

class LoginRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : LoginRemoteDataSource {

    override fun createAccount(email: String, password: String) {
        firebaseAuth.createAccount(email, password)
    }
}