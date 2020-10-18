package br.com.data.source.remote.service.firebase.dao

import android.util.Log
import br.com.domain.entity.User
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

class UserDao : BaseFirestoreDao<User>() {

    init {
        collectionName = GRID_COLLECTION
    }

    fun createAccountStore(user: User?) {
        user?.let {
            user.id?.let {
                db.collection(USER_COLLECTION).document(it)
                    .set(user)
                    .addOnSuccessListener {
                        Log.d(javaClass.name, "DocumentSnapshot added")
                    }
                    .addOnFailureListener { e ->
                        Log.w(javaClass.name, "Error adding document", e)
                    }
            }
        }
    }

    fun getUserInfo(token: String?): DocumentReference? {
        return token?.let { db.collection(USER_COLLECTION).document(it) }
    }

    fun findAll(): Query {
        return collection()
    }

    companion object {
        const val NAME = "name"
        const val RGM = "rgm"
        const val USER_COLLECTION = "users"
        const val GRID_COLLECTION = "grid"
        const val COURSE = "course"
        const val COURSE_NAME = "courseName"
        const val GROUP = "group"
    }
}