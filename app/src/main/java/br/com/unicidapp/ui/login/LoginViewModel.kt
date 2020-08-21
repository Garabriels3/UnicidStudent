package br.com.unicidapp.ui.login

import androidx.lifecycle.ViewModel
import br.com.domain.usecase.login.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun onUsernameTextChanged(text: CharSequence) {

    }

    fun createAccount(email: String, password: String) {
        loginUseCase.createAccount(email, password)
    }
}