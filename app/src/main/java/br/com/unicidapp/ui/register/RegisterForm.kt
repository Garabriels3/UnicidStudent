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

    fun shouldChangeDrawableBorderFieldUserName(): Boolean {
        return nameIsValid()
    }

    fun shouldChangeDrawableBorderFieldEmail(): Boolean {
        return emailIsValid()
    }

    fun shouldChangeDrawableBorderFieldPassword(): Boolean {
        return passwordIsValid()
    }

    fun shouldChangeDrawableBorderFieldRgm(): Boolean {
        return rgmIsValid()
    }

    fun shouldChangeDrawableBorderFieldCourseName(): Boolean {
        return courseNameIsValid()
    }

    fun shouldChangeDrawableBorderFieldSemester(): Boolean {
        return semesterIsValid()
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