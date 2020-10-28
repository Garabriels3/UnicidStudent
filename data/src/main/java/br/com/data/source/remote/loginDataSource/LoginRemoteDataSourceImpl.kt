package br.com.data.source.remote.loginDataSource

import android.util.Log
import br.com.data.mapper.Mapper
import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User
import com.google.firebase.firestore.DocumentSnapshot

class LoginRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao,
    private val mapperUserInfo: Mapper<DocumentSnapshot, User>
) : LoginRemoteDataSource {

    override suspend fun loginAccount(email: String, password: String): FirebaseResponse {
        return firebaseAuth.loginAccount(email, password)
    }

    override suspend fun getUserInfo(token: String, userInfo: (User) -> Unit) {
        userDao.getUserInfo(token)?.get()?.addOnSuccessListener {
            userInfo(mapperUserInfo.transform(it))
        }?.addOnFailureListener {
            Log.w(javaClass.name, "listen:error", it)
        }
    }

    override fun logoutAccount() {
        TODO("Not yet implemented")
    }
}