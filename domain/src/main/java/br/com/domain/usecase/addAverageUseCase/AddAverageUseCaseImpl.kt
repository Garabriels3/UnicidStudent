package br.com.domain.usecase.addAverageUseCase

import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.repository.AddAverageRepository

class AddAverageUseCaseImpl(
    private val addAverageRepository: AddAverageRepository
) : AddAverageUseCase {

    override suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>?) -> Unit
    ) {
        addAverageRepository.getDiscipline(
            courseName,
            currentSemester,
            get
        )
    }

    override suspend fun getStudentNote(token: String, get: (List<AddAverage>?) -> Unit) {
        return addAverageRepository.getStudentNote(token, get)
    }

    override suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse {
        return addAverageRepository.addStudentNote(addAverage, token)
    }

    override suspend fun updateFinalGrade(token: String, addAverage: AddAverage) {
        addAverageRepository.updateFinalGrade(token, addAverage)
    }
}