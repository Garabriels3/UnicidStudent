package br.com.domain.repository

import br.com.domain.entity.SelectionItem

interface AddAverageRepository {
    suspend fun getDiscipline(courseName: String, currentSemester: String, get: (List<SelectionItem>) -> Unit)
}