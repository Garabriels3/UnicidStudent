package br.com.data.source.remote.addAverageDataSource

import android.util.Log
import br.com.data.mapper.Mapper
import br.com.data.source.remote.service.firebase.dao.CourseDao
import br.com.data.source.remote.service.firebase.dao.UserDao
import br.com.domain.entity.AddAverage
import br.com.domain.entity.FirebaseResponse
import br.com.domain.entity.SelectionItem
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class AddAverageRemoteDataSourceImpl(
    private val userDao: UserDao,
    private val courseDao: CourseDao,
    private val mapperOut: Mapper<List<String>, List<SelectionItem>>,
    private val mapperNotes: Mapper<QuerySnapshot, List<AddAverage>?>
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

    override suspend fun addStudentNote(addAverage: AddAverage, token: String): FirebaseResponse {
        return try {
            userDao.addStudentNote(addAverage, token)
            FirebaseResponse()
        } catch (e: FirebaseAuthException) {
            FirebaseResponse(e.toString())
        }
    }

    override suspend fun getStudentNote(token: String, userNotes: (List<AddAverage>?) -> Unit) {
        userDao.getStudentNote(token).addSnapshotListener { document, exception ->
            if (exception != null) {
                Log.w(javaClass.name, "listen:error", exception)
                return@addSnapshotListener
            }
            document?.let {
                userNotes(mapperNotes.transform(it))
            }
        }
    }
}