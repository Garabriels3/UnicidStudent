package br.com.domain.repository

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem

interface AddAverageRepository {
    suspend fun getDiscipline(courseName: String, currentSemester: String, get: (List<SelectionItem>) -> Unit)
    suspend fun getStudentNote(token: String, get: (List<AddAverage>?) -> Unit)
    suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse
    suspend fun updateFinalGrade(token: String, addAverage: AddAverage)
}