package br.com.unicidapp.ui.home

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import br.com.domain.boundary.ResourcesDrawable
import br.com.unicidapp.parcelable.HomeMenu
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class HomeViewModel(
    resourcesDrawable: ResourcesDrawable<Drawable>
) : BaseViewModel() {

    val localMenuOptions: LiveData<List<HomeMenu>> get() = _localMenuOptions
    val boletimClick: LiveData<Boolean> get() = _boletimClick
    val contactClick: LiveData<Boolean> get() = _contactClick
    val newsClick: LiveData<Boolean> get() = _newsClick
    val onConstructionClick: LiveData<Boolean> get() = _onConstructionClick

    private val _boletimClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _contactClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _newsClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _onConstructionClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _localMenuOptions: FlexibleLiveData<List<HomeMenu>> = FlexibleLiveData.default(
        listOf(
            HomeMenu(
                title = BOLETIM_LABEL,
                image = resourcesDrawable.boletimDrawable
            ),
            HomeMenu(
                title = CONTACT_LABEL,
                image = resourcesDrawable.contactsDrawable
            ),
            HomeMenu(
                title = NEWS_LABEL,
                image = resourcesDrawable.newsDrawable
            )
        )
    )

    fun onHomeMenuClick(type: HomeMenu) {
        when (type.title) {
            BOLETIM_LABEL -> _boletimClick.trigger()
            CONTACT_LABEL -> _contactClick.trigger()
            NEWS_LABEL -> _newsClick.trigger()
            else -> _onConstructionClick.trigger()
        }
    }

    companion object {

        private const val BOLETIM_LABEL = "Boletim"
        private const val CONTACT_LABEL = "Contato"
        private const val NEWS_LABEL = "Noticias"
    }
}