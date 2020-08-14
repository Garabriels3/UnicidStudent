package com.example.domain.entity.error

enum class ErrorRequestType(val code: Int) {

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    TOKEN_EXPIRED(409),
    UNPROCESSABLE(422),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503),
    EXCEPTION_INSCRICAO_ALREADY_REPORTED(208);

    companion object {
        fun byCode(code: Int?): ErrorRequestType {
            return try {
                enumValues<ErrorRequestType>().first { it.code == code }
            } catch (e: Exception) {
                INTERNAL_SERVER_ERROR
            }
        }
    }
}