package br.com.unicidapp.utils.resources

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import br.com.domain.boundary.ResourcesDrawable
import br.com.unicidapp.R

class ResourcesDrawableImpl(
    context: Context
) : ResourcesDrawable<Drawable> {

    private val context = context.applicationContext

    override val boletimDrawable: Drawable
        get() = getDrawable(R.drawable.sv_boletim_image_card)

    override val newsDrawable: Drawable
        get() = getDrawable(R.drawable.sv_news)

    override val contactsDrawable: Drawable
        get() = getDrawable(R.drawable.ic_sv_contact)

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getDrawable(resourcesId: Int): Drawable {
        return context.resources.getDrawable(resourcesId)
    }
}