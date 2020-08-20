package br.com.domain.entity

data class ApiError(
    val responseCode: Int?,
    val msgOne: String?,
    val msgTwo: String?
)