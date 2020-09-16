package br.com.unicidapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.entity.SubTest
import br.com.domain.entity.User
import br.com.domain.usecase.login.LoginUseCase
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val enableSignInButton: LiveData<Boolean> get() = _enableLogInButton
    val enableDrawableFieldUserName: LiveData<Boolean> get() = _enableDrawableFieldUserName
    val enableDrawableFieldPassword: LiveData<Boolean> get() = _enableDrawableFieldPassword
    val hideKeyboard: LiveData<Boolean> get() = _hideKeyboard

    private val _enableLogInButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _enableDrawableFieldUserName: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _enableDrawableFieldPassword: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _hideKeyboard: FlexibleLiveData<Boolean> = FlexibleLiveData()

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
        _hideKeyboard.value = true
        loginUseCase.loginAccount(loginForm.userName, loginForm.password)
    }

    fun createUser() {
        val subTest = SubTest("Matematica", listOf("1", "2", "3"))
        val userMock: User = User(
            "Gabriel", "21285136", "Analise e Desenvolvimento de Sistemas", "A", "Adoleta", subTest
        )
        loginUseCase.createAccount(userMock)
    }
}