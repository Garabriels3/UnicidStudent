package br.com.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import br.com.domain.entity.User

class DocumentSnapshotToUserMapper : Mapper<DocumentSnapshot, User>() {

    override fun transform(item: DocumentSnapshot?): User {
        val itemUser = item?.toObject(User::class.java)
        return User(
            name = itemUser?.name ?: "",
            courseName = itemUser?.courseName ?: "",
            semester = itemUser?.semester ?: "",
            email = itemUser?.email ?: "",
            rgm = itemUser?.rgm ?: "",
            id = itemUser?.id ?: ""
        )
    }
}