package br.com.unicidapp.ui.average.addAverage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import br.com.domain.entity.SelectionItem
import br.com.domain.usecase.addAverageUseCase.AddAverageUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class AddAverageViewModel(
    private val addAverageUseCase: AddAverageUseCase
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
            addAverageUseCase.getDiscipline("Comercio Exterior", "1") {
                _listDisciplineNameOptions.value = it
            }
        }
    }

    fun setCourseNameChanged(options: List<SelectionItem>?) {
        options?.let {
            addAverageForm.discipline = it.find { item -> item.isSelected }?.id.toString()
            _enableRegisterButton.value = addAverageForm.shouldEnableButton()
        }
        _listDisciplineNameOptions.value = options
    }

    fun openDisciplineNameSheet() =
        _openDisciplineNameSheet.postValue(_listDisciplineNameOptions.value)
}