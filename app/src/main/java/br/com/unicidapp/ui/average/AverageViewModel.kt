package br.com.unicidapp.ui.average

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import br.com.domain.entity.AddAverage
import br.com.domain.storange.Cache
import br.com.domain.usecase.addAverageUseCase.AddAverageUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class AverageViewModel(
    private val addAverageUseCase: AddAverageUseCase,
    private val cache: Cache
) : BaseViewModel() {

    val goToAddAverage: LiveData<Boolean> get() = _goToAddAverage
    val listAddAverage: LiveData<List<AddAverage>> get() = _listAddAverage

    private val _goToAddAverage: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _listAddAverage: FlexibleLiveData<List<AddAverage>> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @SuppressWarnings("unused")
    fun onStart() {
        cache.getString(ID_KEY, "")?.let {
            launch(baseLoading) {
                addAverageUseCase.getStudentNote(it) { list ->
                    _listAddAverage.value = list
                }
            }
        }
    }

    fun goToAddAverageScreen() {
        _goToAddAverage.trigger()
    }

    companion object {
        private const val COURSE_NAME_KEY = "COURSE_NAME"
        private const val SEMESTER_KEY = "SEMESTER"
        private const val USER_NAME_KEY = "USER_NAME"
        private const val EMAIL_KEY = "EMAIL"
        private const val ID_KEY = "ID"
        private const val RGM_KEY = "RGM"
    }
}