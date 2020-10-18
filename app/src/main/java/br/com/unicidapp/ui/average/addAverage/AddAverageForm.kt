package br.com.unicidapp.ui.average.addAverage

class AddAverageForm {

    var a1: String = ""
    var a2: String = ""
    var discipline: String = ""
    var semester: String = ""
    var courseName: String = ""
    var af: String? = ""

    private fun firstNoteIsValid(): Boolean = a1.isNotEmpty()

    private fun secondNoteIsValid(): Boolean = a2.isNotEmpty()

    private fun disciplineIsValid(): Boolean = discipline.isNotEmpty()

    fun shouldEnableButton(): Boolean {
        return firstNoteIsValid() || secondNoteIsValid() && disciplineIsValid()
    }
}