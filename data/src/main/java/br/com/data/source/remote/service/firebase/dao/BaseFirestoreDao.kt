package br.com.data.source.remote.service.firebase.dao

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

    protected fun find(field: String, value: Any): Query {
        return collection().whereEqualTo(field, value)
    }

    fun collection(): CollectionReference {
        return db.collection(collectionName)
    }
}