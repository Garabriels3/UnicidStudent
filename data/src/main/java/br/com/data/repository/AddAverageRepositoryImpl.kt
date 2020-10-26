package br.com.data.repository

import br.com.data.source.remote.addAverageDataSource.AddAverageRemoteDataSource
import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import br.com.domain.repository.AddAverageRepository

class AddAverageRepositoryImpl(
    private val addAverageRemoteDataSource: AddAverageRemoteDataSource
) : AddAverageRepository {

    override suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    ) {
        addAverageRemoteDataSource.getDiscipline(courseName, currentSemester, get)
    }

    override suspend fun getStudentNote(token: String, get: (List<AddAverage>?) -> Unit) {
        return addAverageRemoteDataSource.getStudentNote(token, get)
    }

    override suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse {
        return addAverageRemoteDataSource.addStudentNote(addAverage, token)
    }

    override suspend fun updateFinalGrade(token: String, addAverage: AddAverage) {
        addAverageRemoteDataSource.updateFinalGrade(token, addAverage)
    }
}