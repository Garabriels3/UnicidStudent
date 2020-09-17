package br.com.unicidapp.ui.register

import br.com.domain.entity.User

class RegisterForm {

    var id: String? = ""
    var email: String = ""
    var password: String = ""
    var name: String = ""
    var rgm: String = ""
    var courseName: String = ""
    var semester: String = ""

    private fun nameIsValid(): Boolean {
        return (name.isNotEmpty())
    }

    private fun emailIsValid(): Boolean {
        return (email.isNotEmpty())
    }

    private fun passwordIsValid(): Boolean {
        return (password.isNotEmpty())
    }

    private fun rgmIsValid(): Boolean {
        return (rgm.isNotEmpty())
    }

    private fun courseNameIsValid(): Boolean {
        return (courseName.isNotEmpty())
    }

    private fun semesterIsValid(): Boolean {
        return (semester.isNotEmpty())
    }

    fun shouldEnableButton(): Boolean {
        return nameIsValid() && rgmIsValid() && courseNameIsValid()
                && semesterIsValid() && emailIsValid() && passwordIsValid()
    }

    fun build() = id?.let {
        User(
            name = name,
            courseName = courseName,
            email = email,
            semester = semester,
            rgm = rgm,
            id = it
        )
    }
}