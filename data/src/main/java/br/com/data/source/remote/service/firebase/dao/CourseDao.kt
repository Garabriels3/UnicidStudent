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
        const val A1 = "a1"
        const val A2 = "a2"
        const val DISCIPLINE = "discipline"
        const val IS_AF = "isAf"
        const val TOTAL_NOTE = "totalNote"
        const val AF = "af"
        const val AFF = "aff"
    }
}