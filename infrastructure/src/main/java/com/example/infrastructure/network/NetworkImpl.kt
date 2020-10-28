package com.example.infrastructure.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkImpl(val context: Context): Network {

    override fun hasActiveInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo
        }
        return activeNetworkInfo != null
    }
}