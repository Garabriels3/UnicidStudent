package br.com.unicidapp.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import br.com.unicidapp.utils.livedata.Event

fun <T : Any> LifecycleOwner.bind(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observeSmart(this) { function.invoke(it) }
}

fun <T : Any> LifecycleOwner.bindEvent(data: LiveData<Event<T>>, function: (id: T) -> Unit) {
    data.observeEvent(this) { function.invoke(it) }
}