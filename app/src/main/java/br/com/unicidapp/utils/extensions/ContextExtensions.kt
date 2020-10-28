package br.com.unicidapp.utils.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getContextCompactDrawable(@DrawableRes drawableId: Int) = ContextCompat.getDrawable(this, drawableId)
fun Context.getContextCompactColor(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)