package br.com.unicidapp.ui.optionDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class OptionsViewModel : ViewModel() {

    val optionSelected: LiveData<List<SelectionItem>> get() = _optionSelected
    val onConfirmOption: LiveData<Unit> get() = _onConfirmOption

    private val _optionSelected: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _onConfirmOption: FlexibleLiveData<Unit> = FlexibleLiveData()

    fun onOptionSelected(option: List<SelectionItem>) {
        _optionSelected.value = option
    }

    fun onConfirmClick() {
        _onConfirmOption.value = Unit
    }

}
