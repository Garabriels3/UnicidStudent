package br.com.unicidapp.utils.extensions

import android.widget.Button
import androidx.annotation.DrawableRes
import br.com.unicidapp.R

fun Button.isEnabled(boolean: Boolean,  resTrue: Int, resFalse: Int) {
    if (boolean) enableButton(resTrue) else disableButton(resFalse)
}

fun Button.enableButton(drawableRes: Int) {
    isEnabled = true
    isClickable = true
    background = context.getContextCompactDrawable(drawableRes)
}

fun Button.disableButton(drawableRes: Int) {
    isEnabled = false
    isClickable = false
    background = context.getContextCompactDrawable(drawableRes)
}
