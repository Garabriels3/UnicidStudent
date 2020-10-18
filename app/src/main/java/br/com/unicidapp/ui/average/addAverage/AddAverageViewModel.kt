package br.com.unicidapp.ui.average.addAverage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import br.com.domain.entity.SelectionItem
import br.com.domain.storange.Cache
import br.com.domain.usecase.addAverageUseCase.AddAverageUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class AddAverageViewModel(
    private val addAverageUseCase: AddAverageUseCase,
    private val cache: Cache
) : BaseViewModel() {

    val openDisciplineNameSheet: LiveData<List<SelectionItem>> get() = _openDisciplineNameSheet
    val listDisciplineNameOptions: LiveData<List<SelectionItem>> get() = _listDisciplineNameOptions
    val enableRegisterButton: LiveData<Boolean> get() = _enableRegisterButton

    private val _openDisciplineNameSheet: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _enableRegisterButton: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _listDisciplineNameOptions: FlexibleLiveData<List<SelectionItem>> =
        FlexibleLiveData()

    private val addAverageForm = AddAverageForm()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @SuppressWarnings("unused")
    fun onCreate() {
        launch(baseLoading) {
            cache.getString(COURSE_NAME_KEY, "")?.let {
                addAverageForm.courseName = it
            }
            cache.getString(
                SEMESTER_KEY, ""
            )?.let {
                addAverageForm.semester = it
            }
            addAverageUseCase.getDiscipline(addAverageForm.courseName, addAverageForm.semester) {
                _listDisciplineNameOptions.value = it
            }
        }
    }

    fun setCourseNameChanged(options: List<SelectionItem>?) {
        options?.let {
            addAverageForm.discipline = it.find { item -> item.isSelected }?.description.toString()
            _enableRegisterButton.value = addAverageForm.shouldEnableButton()
        }
        _listDisciplineNameOptions.value = options
    }

    fun onFirstNoteChanged(text: CharSequence?) {
        text?.let {
            addAverageForm.a1 = it.toString()
        }
    }

    fun onSecondNoteChanged(text: CharSequence?) {
        text?.let {
            addAverageForm.a2 = it.toString()
        }
    }

    fun openDisciplineNameSheet() =
        _openDisciplineNameSheet.postValue(_listDisciplineNameOptions.value)

    companion object {
        private const val COURSE_NAME_KEY = "COURSE_NAME"
        private const val SEMESTER_KEY = "SEMESTER"
        private const val USER_NAME_KEY = "USER_NAME"
        private const val EMAIL_KEY = "EMAIL"
        private const val ID_KEY = "ID"
        private const val RGM_KEY = "RGM"
    }
}