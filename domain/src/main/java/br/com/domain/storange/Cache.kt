package br.com.domain.storange

interface Cache {
    fun get(key: String, value: String): String?
    fun set(key: String, value: String?)
    fun clear()
    class NotFoundException : Exception()
}