package com.example.infrastructure.network

interface Network {
    fun hasActiveInternetConnection(): Boolean
}