package br.com.domain.entity

import br.com.domain.entity.error.ErrorRequestType

data class RequestError(
    val status: ErrorRequestType? = null,
    val errorMessage: String? = null
)