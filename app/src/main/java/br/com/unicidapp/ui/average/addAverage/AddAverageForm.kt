package br.com.unicidapp.ui.average.addAverage

import br.com.domain.entity.AddAverage

class AddAverageForm {

    var a1: String = ""
    var a2: String = ""
    var totalNote: String = ""
    var highestNote: Float = 0F
    var discipline: String = ""
    var semester: String = ""
    var courseName: String = ""
    var af: String = ""
    var isAf: Boolean? = false
    var isReprove: Boolean? = false
    var isApprove: Boolean? = false
    var addAverage: AddAverage? = null

    private fun firstNoteIsValid(): Boolean = a1.isNotEmpty()

    private fun secondNoteIsValid(): Boolean = a2.isNotEmpty()

    private fun afNoteIsValid(): Boolean = af.isNotEmpty()

    private fun disciplineIsValid(): Boolean = discipline.isNotEmpty()

    fun shouldEnableButton(): Boolean {
        return firstNoteIsValid() && secondNoteIsValid() && disciplineIsValid() || afNoteIsValid()
    }

    fun build(): AddAverage {
        return AddAverage(
            a1 = a1,
            a2 = a2,
            discipline = discipline,
            totalNote = totalNote,
            afState = isAf,
            approveState = isApprove,
            reproveState = isReprove,
            afNote = af
        )
    }

    fun buildUpdate(): AddAverage {
        return AddAverage(
            id = addAverage?.id,
            afState = isAf,
            approveState = isApprove,
            reproveState = isReprove,
            afNote = af,
            totalNote = totalNote
        )
    }
}