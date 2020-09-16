package br.com.unicidapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.entity.Education
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class RegisterViewModel : ViewModel() {


    val openCourseNameSheet: LiveData<List<SelectionItem>> get() = _openCourseNameSheet
    val listCourseNameOptions: LiveData<List<SelectionItem>> get() = _listCourseNameOptions
    val education: LiveData<Education> get() = _education

    private val _openCourseNameSheet: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _listCourseNameOptions: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _education: FlexibleLiveData<Education> = FlexibleLiveData()

    fun setCourseNameChanged(options: List<SelectionItem>?) {
        options?.let {
            _education.value?.courseName = it.find { item -> item.isSelected }?.description
        }
        _listCourseNameOptions.value = options
    }

    fun openCourseNameSheet() = _openCourseNameSheet.postValue(_listCourseNameOptions.value)
}
