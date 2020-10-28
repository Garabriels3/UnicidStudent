package br.com.unicidapp.ui.login

import androidx.lifecycle.LiveData
import br.com.domain.usecase.loginUseCase.LoginUseCase
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import br.com.domain.storange.Cache

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val cache: Cache
) : BaseViewModel() {

    val enableSignInButton: LiveData<Boolean> get() = _enableLogInButton
    val enableDrawableFieldUserName: LiveData<Boolean> get() = _enableDrawableFieldUserName
    val enableDrawableFieldPassword: LiveData<Boolean> get() = _enableDrawableFieldPassword
    val onStartRegister: LiveData<Boolean> get() = _onStartRegister
    val onErrorDialog: LiveData<Boolean> get() = _onErrorDialog
    val goToHome: LiveData<Boolean> get() = _goToHome

    private val _enableLogInButton: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _enableDrawableFieldUserName: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _enableDrawableFieldPassword: FlexibleLiveData<Boolean> =
        FlexibleLiveData()
    private val _onStartRegister: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _onErrorDialog: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _goToHome: FlexibleLiveData<Boolean> = FlexibleLiveData()

    private var loginForm = LoginForm()

    fun onUsernameTextChanged(text: CharSequence) {
        loginForm.email = text.toString()
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
            val result = loginUseCase.loginAccount(loginForm.email, loginForm.password)
            if (result.isSuccess()) {
                getInfo(result.userUid)
                _goToHome.trigger()
            } else {
                _onErrorDialog.trigger()
            }
        }
    }

    private fun getInfo(token: String?) {
        launch(baseLoading) {
            token?.let {
                loginUseCase.getUserInfo(token) { user ->
                    user.courseName?.let { courseName ->
                        cache.setString(
                            COURSE_NAME_KEY,
                            courseName
                        )
                    }
                    user.semester?.let { semester -> cache.setString(SEMESTER_KEY, semester) }
                    user.name?.let { name -> cache.setString(USER_NAME_KEY, name) }
                    user.email?.let { email -> cache.setString(EMAIL_KEY, email) }
                    user.id?.let { id -> cache.setString(ID_KEY, id) }
                    user.rgm?.let { rgm -> cache.setString(RGM_KEY, rgm) }
                }
            }
        }
    }

    fun navigateToRegister() {
        _onStartRegister.trigger()
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