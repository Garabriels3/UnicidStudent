package com.example.domain.entity

import com.example.domain.entity.error.ErrorRequestType

data class RequestError(
    val status: ErrorRequestType? = null,
    val errorMessage: String? = null
)