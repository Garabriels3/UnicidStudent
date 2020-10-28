package br.com.data.source.remote.addAverageDataSource

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User

interface AddAverageRemoteDataSource {
    suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    )

    suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse
    suspend fun getStudentNote(token: String, userNotes: (List<AddAverage>?) -> Unit)
    suspend fun updateFinalGrade(token: String, addAverage: AddAverage)
}