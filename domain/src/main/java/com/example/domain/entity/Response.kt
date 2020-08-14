package com.example.domain.entity

sealed class Response<T>
class EmptyResponse<T>(val error: RequestError) : Response<T>()
data class SuccessResponse<T>(val body: T) : Response<T>()
data class ErrorResponse<T>(val error: RequestError) : Response<T>()