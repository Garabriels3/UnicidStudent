package br.com.unicidapp.ui.login

class LoginForm {

    var userName: String = ""
    var password: String = ""

    private fun userNameIsValid(): Boolean {
        return (userName.isNotEmpty())
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