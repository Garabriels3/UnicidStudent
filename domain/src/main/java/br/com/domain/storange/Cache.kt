package br.com.domain.storange

interface Cache {
    fun getString(key: String, value: String): String?
    fun getBoolean(key: String, value: Boolean): Boolean?
    fun getInt(key: String, value: Int): Int
    fun setString(key: String, value: String?)
    fun setInt(key: String, value: Int?)
    fun setBoolean(key: String, value: Boolean?)
    fun clear()
    class NotFoundException : Exception()
}