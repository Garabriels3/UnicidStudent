package br.com.domain.entity

data class DisciplineAverage(
    val disciplineName: String,
    val disciplineNote: Float,
    val isApproved: Boolean? = null,
    val isFinalRating: Boolean = false
)