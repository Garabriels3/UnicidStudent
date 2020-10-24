package br.com.data.model.firestore

import br.com.data.source.remote.service.firebase.dao.CourseDao
import com.google.firebase.firestore.PropertyName

data class AddAverageData(
    @PropertyName(CourseDao.A1) val a1: String? = null,
    @PropertyName(CourseDao.A2) val a2: String? = null,
    @PropertyName(CourseDao.AF) val af: String? = null,
    @PropertyName(CourseDao.AFF) val aff: String? = null,
    @PropertyName(CourseDao.DISCIPLINE) val discipline: String? = null,
    @PropertyName(CourseDao.TOTAL_NOTE) val totalNote: String? = null
)