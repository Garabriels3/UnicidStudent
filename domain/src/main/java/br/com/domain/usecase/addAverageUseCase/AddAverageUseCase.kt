package br.com.domain.usecase.addAverageUseCase

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem

interface AddAverageUseCase {
    suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    )

    suspend fun getStudentNote(
        token: String,
        get: (List<AddAverage>?) -> Unit
    )

    suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse
}