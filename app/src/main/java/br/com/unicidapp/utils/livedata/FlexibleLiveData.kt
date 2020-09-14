package br.com.unicidapp.utils.livedata

import androidx.lifecycle.MutableLiveData

class FlexibleLiveData<T> : MutableLiveData<T>() {

    companion object {
        fun <T> default(t: T?): FlexibleLiveData<T> {
            val liveData = FlexibleLiveData<T>()
            liveData.value = t
            return liveData
        }
    }
}