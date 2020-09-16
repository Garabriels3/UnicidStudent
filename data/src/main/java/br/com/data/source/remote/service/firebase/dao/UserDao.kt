package br.com.data.source.remote.service.firebase.dao

import android.util.Log
import br.com.data.model.firestore.CourseData
import br.com.data.model.firestore.UserData
import br.com.domain.entity.User

class UserDao : BaseFirestoreDao<UserData>() {

    fun createAccountStore(user: User) {
        db.collection("users").document("21285136").collection("materias")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(javaClass.name, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(javaClass.name, "Error adding document", e)
            }
    }

    companion object {
        const val NAME = "name"
        const val RGM = "rgm"
        const val COURSE = "course"
        const val COURSE_NAME = "courseName"
        const val GROUP = "group"
    }
}