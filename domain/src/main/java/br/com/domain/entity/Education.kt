package br.com.domain.entity

import java.io.Serializable

data class Education(
    var courseName: String? = null,
    var semester: String? = null
) : Serializable