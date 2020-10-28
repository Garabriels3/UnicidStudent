package br.com.unicidapp.utils

import android.content.Context
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import br.com.unicidapp.R

fun applyDrawable(editText: EditText, @DrawableRes drawable: Int, context: Context) {
    editText.background = ContextCompat.getDrawable(context, drawable)
}

fun applyColorEditTextBorder(editText: EditText, enable: Boolean, context: Context) {
    applyDrawable(
        editText,
        if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
        context
    )
}
