package br.com.data.repository

import br.com.data.source.remote.addAverageDataSource.AddAverageRemoteDataSource
import br.com.domain.entity.SelectionItem
import br.com.domain.repository.AddAverageRepository

class AddAverageRepositoryImpl(
    private val addAverageRemoteDataSource: AddAverageRemoteDataSource
) : AddAverageRepository {

    override suspend fun getDiscipline(courseName: String, currentSemester: String, get: (List<SelectionItem>) -> Unit) {
        addAverageRemoteDataSource.getDiscipline(courseName, currentSemester, get)
    }
}