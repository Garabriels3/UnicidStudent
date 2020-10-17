package br.com.domain.usecase.addAverageUseCase

import br.com.domain.entity.SelectionItem

interface AddAverageUseCase {
    suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    )
}