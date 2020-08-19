package com.example.data.model

sealed class DataException(override val message: String?) : Throwable(message) {

    class MinSDKSharedPreferencesException(minSdkVersion: Int) :
        DataException("SharedPreferences SDK min to apply is $minSdkVersion")
}