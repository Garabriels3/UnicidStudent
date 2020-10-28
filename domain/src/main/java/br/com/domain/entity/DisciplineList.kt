package br.com.domain.entity

import java.io.Serializable

data class DisciplineList(
    var disciplineName: List<String>? = null
) : Serializable