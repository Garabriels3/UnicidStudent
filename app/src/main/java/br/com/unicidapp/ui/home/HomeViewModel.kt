package br.com.unicidapp.ui.home

import android.graphics.drawable.Drawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import br.com.domain.boundary.ResourcesDrawable
import br.com.domain.entity.User
import br.com.domain.usecase.homeUseCase.HomeUseCase
import br.com.unicidapp.parcelable.HomeMenu
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class HomeViewModel(
    private val resourcesDrawable: ResourcesDrawable<Drawable>,
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    val localMenuOptions: LiveData<List<HomeMenu>> get() = _localMenuOptions
    val boletimClick: LiveData<Boolean> get() = _boletimClick
    val contactClick: LiveData<Boolean> get() = _contactClick
    val newsClick: LiveData<Boolean> get() = _newsClick
    val userInfo: LiveData<User> get() = _userInfo
    val isSignOut: LiveData<Boolean> get() = _isSignOut

    private val _boletimClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _contactClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _newsClick: FlexibleLiveData<Boolean> = FlexibleLiveData()
    private val _userInfo: FlexibleLiveData<User> = FlexibleLiveData()
    private val _isSignOut: FlexibleLiveData<Boolean> = FlexibleLiveData()
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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @SuppressWarnings("unused")
    fun onCreate() {
        launch(baseLoading, launchWithDelay = true) {
            val item = homeUseCase.getCurrentUser()

            if (item.isSuccess()) {
                item.userUid?.let { uid ->
                    homeUseCase.getInfo(uid) {
                        _userInfo.value = it
                    }
                }
            }
        }
    }

    fun onHomeMenuClick(type: HomeMenu) {
        when (type.title) {
            BOLETIM_LABEL -> _boletimClick.trigger()
            CONTACT_LABEL -> _contactClick.trigger()
            NEWS_LABEL -> _newsClick.trigger()
        }
    }

    fun signOutAndClearCache() {
        launch {
            val result = homeUseCase.signOut()
            if (result.isSuccess()) {
                _isSignOut.trigger()
            }
        }
    }

    companion object {
        private const val BOLETIM_LABEL = "Boletim"
        private const val CONTACT_LABEL = "Contato"
        private const val NEWS_LABEL = "Noticias"
        private const val COURSE_NAME_KEY = "COURSE_NAME"
        private const val SEMESTER_KEY = "SEMESTER"
        private const val USER_NAME_KEY = "USER_NAME"
        private const val EMAIL_KEY = "EMAIL"
        private const val ID_KEY = "ID"
        private const val RGM_KEY = "RGM"
    }
}