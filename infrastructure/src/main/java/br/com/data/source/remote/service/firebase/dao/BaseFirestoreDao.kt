package br.com.data.source.remote.service.firebase.dao

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query

open class BaseFirestoreDao<T : Any> {
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    protected var collectionName: String = ""

    init {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings
    }

    fun insert(obj: T) {
        collection()
            .add(obj)
            .addOnSuccessListener { documentReference ->
                Log.d(javaClass.name, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(javaClass.name, "Error adding document", e)
            }
    }

    fun insert(obj: T, documentId: String) {
        collection().document(documentId).set(obj)
            .addOnSuccessListener { documentReference ->
                Log.d(javaClass.name, "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w(javaClass.name, "Error adding document", e)
            }
    }

    protected fun find(field: String, value: Any): Query {
        return collection().whereEqualTo(field, value)
    }

    fun collection(): CollectionReference {
        return db.collection(collectionName)
    }
}