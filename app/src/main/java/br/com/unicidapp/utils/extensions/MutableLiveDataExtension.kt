package br.com.unicidapp.utils.extensions

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.trigger() {
    value = true
}