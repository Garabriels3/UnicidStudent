package br.com.data.source.remote

interface LoginRemoteDataSource {
    fun createAccount(email: String, password: String)
}