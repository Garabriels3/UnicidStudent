package br.com.data.source.remote.registerDataSource

import android.util.Log
import br.com.data.mapper.Mapper
import br.com.data.source.remote.service.firebase.FirebaseAuth
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class RegisterRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao,
    private val mapperOut: Mapper<QuerySnapshot, List<SelectionItem>>
) : RegisterRemoteDataSource {

    override suspend fun createAccount(email: String, password: String): FirebaseResponse {
        return  firebaseAuth.createAccount(email, password)
    }

    override suspend fun createAccountStore(user: User?): FirebaseResponse {
        return try {
            userDao.createAccountStore(user)
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(e.toString())
        }
    }

    override suspend fun getGrid(get: (List<SelectionItem>) -> Unit) {
        try {
            userDao.findAll().addSnapshotListener { document, exception ->
                if (exception != null) {
                    Log.w(javaClass.name, "listen:error", exception)
                    return@addSnapshotListener
                }
                document?.let {
                    get(mapperOut.transform(it))
                }
            }

        } catch (e: FirebaseFirestoreException) {
            FirebaseResponse(e.toString())
        }
    }
}