package br.com.unicidapp.ui.optionDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class OptionsViewModel(
) : ViewModel() {

    val optionSelected: LiveData<List<SelectionItem>> get() = _optionSelected
    val onConfirmOption: LiveData<Unit> get() = _onConfirmOption
    val enableButton: LiveData<Boolean> get() = _enableButton

    private val _optionSelected: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _onConfirmOption: FlexibleLiveData<Unit> = FlexibleLiveData()
    private val _enableButton: FlexibleLiveData<Boolean> = FlexibleLiveData()

    fun onOptionSelected(option: List<SelectionItem>) {
        _optionSelected.value = option
        _enableButton.value = option.find { it.isSelected }?.isSelected ?: false
    }

    fun onConfirmClick() {
        _onConfirmOption.value = Unit
    }

}
