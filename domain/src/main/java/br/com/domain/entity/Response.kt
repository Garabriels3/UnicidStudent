package br.com.domain.entity

sealed class Response<out T> {

    object EmptyResponse : Response<Nothing>()
    data class SuccessResponse<T>(val body: T) : Response<T>()
    data class ErrorResponse<T>(val error: RequestError) : Response<T>()
}
