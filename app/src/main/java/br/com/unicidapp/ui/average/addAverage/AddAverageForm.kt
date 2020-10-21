package br.com.unicidapp.ui.average.addAverage

import br.com.domain.entity.AddAverage

class AddAverageForm {

    var a1: String = ""
    var a2: String = ""
    var totalNote: String = ""
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

    fun build(): AddAverage {
        return AddAverage(
            a1 = a1,
            a2 = a2,
            discipline = discipline,
            totalNote = totalNote
        )
    }
}