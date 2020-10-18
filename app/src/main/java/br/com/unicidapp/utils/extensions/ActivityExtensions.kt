package br.com.unicidapp.utils.extensions

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import br.com.unicidapp.R

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?,
    showHome: Boolean = true,
    title: String? = null,
    @DrawableRes resourceId: Int = R.drawable.ic_left_arrow_black
) {
    if (title != null) {
        setupToolbarWithTitle(toolbar, title, showHome, resourceId = resourceId)
    } else {
        setupToolbar(toolbar, showHome, resourceId = resourceId)
    }
    toolbar?.navigationContentDescription = resources.getString(R.string.backButton)
    toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.blue_unicid))
}

private fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?,
    showHome: Boolean, @DrawableRes resourceId: Int
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        toolbar?.also {
            setSupportActionBar(it)
            setDisplayHomeAsUpEnabled(showHome)
            setDisplayShowHomeEnabled(showHome)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(resourceId)
        }
    }
}

private fun AppCompatActivity.setupToolbarWithTitle(
    toolbar: Toolbar?,
    title: String,
    showHome: Boolean, @DrawableRes resourceId: Int
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        toolbar?.title = title
        setSupportActionBar(toolbar)
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
        setHomeAsUpIndicator(resourceId)
    }
}