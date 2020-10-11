package br.com.data.model

sealed class DataException(override val message: String?) : Throwable(message) {

    class MinSDKSharedPreferencesException(minSdkVersion: Int) :
        DataException("SharedPreferences SDK min to apply is $minSdkVersion")
}