package br.com.unicidapp.utils.extensions

inline fun <reified T> Any.nullableCast() = takeIf { this is T }?.let { this as T }