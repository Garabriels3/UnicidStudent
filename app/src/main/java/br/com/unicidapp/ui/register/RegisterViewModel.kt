package br.com.unicidapp.ui.register

import androidx.lifecycle.LiveData
import br.com.domain.entity.SelectionItem
import br.com.domain.usecase.RegisterUseCase.RegisterUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    val openCourseNameSheet: LiveData<List<SelectionItem>> get() = _openCourseNameSheet
    val enableRegisterButton: LiveData<Boolean> get() = _enableRegisterButton
    val userNameBorderColor: LiveData<Boolean> get() = _userNameBorderColor
    val passwordBorderColor: LiveData<Boolean> get() = _passwordBorderColor
    val emailBorderColor: LiveData<Boolean> get() = _emailBorderColor
    val courseNameBorderColor: LiveData<Boolean> get() = _courseNameBorderColor
    val semesterBorderColor: LiveData<Boolean> get() = _semesterBorderColor
    val rgmBorderColor: LiveData<Boolean> get() = _rgmBorderColor
    val errorDialog: LiveData<Boolean> get() = _errorDialog

    private val _openCourseNameSheet: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _listCourseNameOptions: FlexibleLiveData<List<SelectionItem>> = FlexibleLiveData()
    private val _enableRegisterButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _userNameBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _passwordBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _emailBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _courseNameBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _semesterBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _rgmBorderColor: FlexibleLiveData<Boolean> = FlexibleLiveData()
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
        _userNameBorderColor.value = registerForm.shouldChangeDrawableBorderFieldUserName()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onEmailChanged(text: CharSequence) {
        registerForm.email = text.toString()
        _emailBorderColor.value = registerForm.shouldChangeDrawableBorderFieldEmail()

        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onPasswordChanged(text: CharSequence) {
        registerForm.password = text.toString()
        _passwordBorderColor.value = registerForm.shouldChangeDrawableBorderFieldPassword()

        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onRgmChanged(text: CharSequence) {
        registerForm.rgm = text.toString()
        _rgmBorderColor.value = registerForm.shouldChangeDrawableBorderFieldRgm()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun onSemesterChanged(text: CharSequence) {
        registerForm.semester = text.toString()
        _semesterBorderColor.value = registerForm.shouldChangeDrawableBorderFieldSemester()
        _enableRegisterButton.value = registerForm.shouldEnableButton()
    }

    fun createAccount() {
        launch(baseLoading) {
            val result =
                registerUseCase.createAccount(registerForm.email, registerForm.password)
            result.let {
                registerForm.id = it.userUid
                registerUseCase.createAccountStore(registerForm.build())
                _errorDialog.value = it.isSuccess()
            }
        }
    }

    fun openCourseNameSheet() = _openCourseNameSheet.postValue(_listCourseNameOptions.value)
}
