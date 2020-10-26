package br.com.data.source.remote.homeDataSource

import android.util.Log
import br.com.data.mapper.Mapper
import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.User
import com.google.firebase.firestore.DocumentSnapshot

class HomeRemoteDataSourceImpl(
    private val auth: FirebaseAuth,
    private val userDao: UserDao,
    private val mapperUserInfo: Mapper<DocumentSnapshot, User>
) : HomeRemoteDataSource {

    override suspend fun signOut(): FirebaseResponse {
        return auth.logoutAccount()
    }

    override suspend fun getInfo(token: String, userInfo: (User) -> Unit) {
        userDao.getUserInfo(token)?.get()?.addOnSuccessListener {
            userInfo(mapperUserInfo.transform(it))
        }?.addOnFailureListener {
            Log.w(javaClass.name, "listen:error", it)
        }
    }

    override suspend fun getCurrentUser(): FirebaseResponse {
        return auth.getCurrentUser()
    }
}