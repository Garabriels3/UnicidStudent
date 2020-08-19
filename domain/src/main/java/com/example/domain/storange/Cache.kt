package com.example.domain.storange

import java.lang.reflect.Type

interface Cache {
    fun get(key: String, value: String): String?
    fun set(key: String, value: String?)
    fun clear()
    class NotFoundException : Exception()
}