package br.com.data.source.remote.addAverageDataSource

import br.com.domain.entity.SelectionItem

interface AddAverageRemoteDataSource {
    suspend fun getDiscipline(courseName: String, currentSemester: String, get: (List<SelectionItem>) -> Unit)
}