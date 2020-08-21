package br.com.unicidapp.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import br.com.unicidapp.utils.livedata.Event
import br.com.unicidapp.utils.livedata.EventObserver

fun <T> LiveData<T>.observeSmart(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, androidx.lifecycle.Observer { it?.also { source -> observer(source) } })
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, EventObserver(observer))
}

