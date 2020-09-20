package br.com.unicidapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.entity.*
import br.com.domain.usecase.RegisterUseCase.RegisterUseCase
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    val openCourseNameSheet: LiveData<List<SelectionItem>> get() = _openCourseNameSheet
    val enableRegisterButton: LiveData<Boolean> get() = _enableRegisterButton
    val errorDialog: LiveData<Boolean> get() = _errorDialog

    private val _openCourseNameSheet: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _listCourseNameOptions: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _enableRegisterButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _errorDialog: FlexibleLiveData<Boolean> = FlexibleLiveData()

    private var registerForm = RegisterForm()

    fun onCreate() {
        CoroutineScope(Dispatchers.Main).launch {
            registerUseCase.getGrid {
                _listCourseNameOptions.value = it
            }
        }
    }

    fun setCourseNameChanged(options: List<SelectionItem>?) {
        options?.let {
            registerForm.courseName = it.find { item -> item.isSelected }?.id.toString()
            _enableRegisterButton.value = registerForm.shouldEnableButton()
        }
        _listCourseNameOptions.value = options
    }

    fun onNameChanged(text: CharSequence) {
        registerForm.name = text.toString()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onEmailChanged(text: CharSequence) {
        registerForm.email = text.toString()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onPasswordChanged(text: CharSequence) {
        registerForm.password = text.toString()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onRgmChanged(text: CharSequence) {
        registerForm.rgm = text.toString()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onSemesterChanged(text: CharSequence) {
        registerForm.semester = text.toString()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun createAccount() {
        CoroutineScope(Dispatchers.Main).launch {
            val result =
                registerUseCase.createAccount(registerForm.email, registerForm.password)
            result.let {
                registerForm.id = it.userUid
                registerUseCase.createAccountStore(registerForm.build())
                if (it.isSuccess()) {
                    _errorDialog.value = true
                }
            }
        }
    }

    fun openCourseNameSheet() = _openCourseNameSheet.postValue(_listCourseNameOptions.value)
}
