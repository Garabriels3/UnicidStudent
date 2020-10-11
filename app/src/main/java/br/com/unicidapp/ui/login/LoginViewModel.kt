package br.com.unicidapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.usecase.login.LoginUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val enableSignInButton: LiveData<Boolean> get() = _enableLogInButton
    val enableDrawableFieldUserName: LiveData<Boolean> get() = _enableDrawableFieldUserName
    val enableDrawableFieldPassword: LiveData<Boolean> get() = _enableDrawableFieldPassword
    val onStartRegister: LiveData<Boolean> get() = _onStartRegister
    val onErrorDialog: LiveData<Boolean> get() = _onErrorDialog

    private val _enableLogInButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _enableDrawableFieldUserName: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _enableDrawableFieldPassword: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _onStartRegister: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _onErrorDialog: FlexibleLiveData<Boolean> = FlexibleLiveData()

    private var loginForm = LoginForm()

    fun onUsernameTextChanged(text: CharSequence) {
        loginForm.userName = text.toString()
        _enableDrawableFieldUserName.value = loginForm.shouldChangeDrawableBorderFieldUserName()
        _enableLogInButton.value = loginForm.shouldEnableSigInButton()
    }

    fun onPasswordTextChanged(text: CharSequence) {
        loginForm.password = text.toString()
        _enableDrawableFieldPassword.value = loginForm.shouldChangeDrawableBorderFieldPassword()
        _enableLogInButton.value = loginForm.shouldEnableSigInButton()
    }

    fun loginAccount() {
        dismissKeyboard()
        launch(baseLoading) {
            val result = loginUseCase.loginAccount(loginForm.userName, loginForm.password)
            _onErrorDialog.value = result.isSuccess()
        }
    }

    fun navigateToRegister() {
        _onStartRegister.value = true
    }
}