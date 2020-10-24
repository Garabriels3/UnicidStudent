package br.com.unicidapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object DefaultMasks {

    const val CPF_MASK = 0
    const val PHONE_MASK = 1
    const val DATE_MASK = 2
    const val CEP_MASK = 3
    const val RG_MASK = 4
    const val MONEY_MASK = 5
    const val MONTH_YEAR = 6
    const val DECIMAL_MASK = 7

    private const val maskCPF = "###.###.###-##"
    private const val maskPhone9 = "(##) #####-####"
    private const val maskPhone = "(##) ####-####"
    private const val maskCEP = "#####-###"
    private const val maskBirthDate = "##/##/####"
    private const val maskRG = "##.###.###-#"
    private const val maskMoney = "R$ #.###,##"
    private const val maskMonthYear = "##/####"
    private const val decimal = "#.#"

    fun unmask(s: String): String {
        return s.replace("[^0-9]*".toRegex(), "")
    }

    fun insert(editText: EditText, type: Int): TextWatcher {
        return object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                val mask = when (type) {
                    PHONE_MASK -> if (str.length >= 11) maskPhone9 else maskPhone
                    DATE_MASK -> maskBirthDate
                    CEP_MASK -> maskCEP
                    RG_MASK -> maskRG
                    MONEY_MASK -> maskMoney
                    MONTH_YEAR -> maskMonthYear
                    DECIMAL_MASK -> decimal
                    else -> maskCPF
                }

                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                //Nothing to do here
            }

            override fun afterTextChanged(s: Editable) {
                //Nothing to do here
            }
        }
    }

}