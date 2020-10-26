package br.com.unicidapp.utils.base

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), LifecycleObserver, KoinComponent {

    val loading: LiveData<Boolean> get() = baseLoading
    val hideKeyboard: LiveData<Boolean> get() = baseHideKeyboard

    protected val baseLoading = MutableLiveData<Boolean>()
    private val baseHideKeyboard: FlexibleLiveData<Boolean> = FlexibleLiveData()

    private val uiScope = CoroutineScope(Dispatchers.Main + Job())

    protected fun launch(
        loadingLiveData: MutableLiveData<Boolean>? = null,
        launchWithDelay: Boolean = false,
        delay: Long = DEFAULT_DELAY,
        block: suspend CoroutineScope.() -> Unit
    ) = uiScope.launch {
        try {
            loadingLiveData?.value = true
            if (launchWithDelay) delay(delay)
            block()
        } catch (e: Exception) {
            Log.d("Execption Key", e.toString())
        } finally {
            loadingLiveData?.value = false
        }
    }

    fun dismissKeyboard() {
        baseHideKeyboard.trigger()
    }

    companion object {
        const val EMPTY_TEXT = ""
        const val DEFAULT_DELAY = 750L
        const val NO_VALUE_LONG = -1L
    }
}