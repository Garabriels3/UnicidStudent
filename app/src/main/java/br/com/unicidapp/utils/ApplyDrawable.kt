package br.com.unicidapp.utils

import android.content.Context
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun applyDrawable(editText: EditText, @DrawableRes drawable: Int, context: Context) {
    editText.background = ContextCompat.getDrawable(context, drawable)
}