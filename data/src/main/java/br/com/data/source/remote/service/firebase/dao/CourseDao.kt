package br.com.data.source.remote.service.firebase.dao

import br.com.domain.entity.DisciplineList
import com.google.firebase.firestore.DocumentReference

class CourseDao : BaseFirestoreDao<DisciplineList>() {

    init {
        collectionName = GRID_COLLECTION
    }

    fun fetchDiscipline(courseName: String): DocumentReference {
        return collection().document(courseName)
    }

    companion object {
        const val GRID_COLLECTION = "grid"
    }
}