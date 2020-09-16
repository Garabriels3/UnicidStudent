package br.com.unicidapp.utils.extensions

import android.widget.Button
import br.com.unicidapp.R

fun Button.isEnabled(boolean: Boolean) {
    if (boolean) enableButton() else disableButton()
}

fun Button.enableButton() {
    isEnabled = true
    isClickable = true
    background = context.getContextCompactDrawable(R.drawable.circle_green_button)
}

fun Button.disableButton() {
    isEnabled = false
    isClickable = false
    background = context.getContextCompactDrawable(R.drawable.circle_gray_button)
}
