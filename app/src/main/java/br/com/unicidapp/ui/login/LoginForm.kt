package br.com.unicidapp.ui.login

import br.com.domain.utils.RegexUtil.EMAIL_REGEX

class LoginForm {

    var email: String = ""
    var password: String = ""

    private fun userNameIsValid(): Boolean {
        return (email.isNotEmpty()) && EMAIL_REGEX.matches(email)
    }

    private fun passwordIsValid(): Boolean {
        return (password.isNotEmpty())
    }

    fun shouldChangeDrawableBorderFieldPassword(): Boolean {
        return passwordIsValid()
    }

    fun shouldChangeDrawableBorderFieldUserName(): Boolean {
        return userNameIsValid()
    }

    fun shouldEnableSigInButton(): Boolean {
        return userNameIsValid() && passwordIsValid()
    }
}