package br.com.domain.usecase.addAverageUseCase

import br.com.domain.entity.SelectionItem
import br.com.domain.repository.AddAverageRepository

class AddAverageUseCaseImpl(
    private val addAverageRepository: AddAverageRepository
) : AddAverageUseCase {

    override suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    ) {
        addAverageRepository.getDiscipline(
            courseName,
            currentSemester,
            get
        )
    }
}