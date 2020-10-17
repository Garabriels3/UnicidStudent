package br.com.data.source.remote.addAverageDataSource

import br.com.data.mapper.Mapper
import br.com.data.source.remote.service.firebase.dao.CourseDao
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException

class AddAverageRemoteDataSourceImpl(
    private val courseDao: CourseDao,
    private val mapperOut: Mapper<List<String>, List<SelectionItem>>
) : AddAverageRemoteDataSource {

    override suspend fun getDiscipline(
        courseName: String,
        currentSemester: String,
        get: (List<SelectionItem>) -> Unit
    ) {
        try {
            courseDao.fetchDiscipline(courseName).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        if (document.exists()) {
                            get(mapperOut.transform(document.get(currentSemester) as List<String>))
                        }
                    }
                }
            }
        } catch (e: FirebaseFirestoreException) {
            FirebaseResponse(e.toString())
        }
    }
}