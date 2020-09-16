package br.com.data.model.firestore

import br.com.data.source.remote.service.firebase.dao.UserDao
import com.google.firebase.firestore.PropertyName

data class UserData(
    @PropertyName(UserDao.NAME) val name: String? = null,
    @PropertyName(UserDao.RGM) val rgm: String? = null,
    @PropertyName(UserDao.COURSE_NAME) val courseName: String? = null,
    @PropertyName(UserDao.GROUP) val group: String? = null
)