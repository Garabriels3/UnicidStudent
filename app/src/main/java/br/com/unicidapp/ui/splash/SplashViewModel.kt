package br.com.unicidapp.ui.splash

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import br.com.domain.storange.Cache
import br.com.unicidapp.BuildConfig
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.delay

class SplashViewModel(
    private val cache: Cache
) : BaseViewModel() {

    val goToLogin: LiveData<Boolean> get() = _goToLogin
    val onBoardingInit: LiveData<Boolean> get() = _onBoardingInit

    private val _goToLogin: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _onBoardingInit: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)

    private val delayMillsToDurationSplashScreen = 5000L

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @SuppressWarnings("unused")
    fun checkFirstRun() {
        val PREF_VERSION_CODE_KEY = "version_code"
        val DOESNT_EXIST = -1

        // Get current version code
        val currentVersionCode: Int = BuildConfig.VERSION_CODE

        val savedVersionCode = cache.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        // Check for first run or upgrade
        launch(baseLoading) {
            delay(delayMillsToDurationSplashScreen)
            _goToLogin.value = true
            when {
                currentVersionCode == savedVersionCode -> {
                    return@launch
                }
                savedVersionCode == DOESNT_EXIST -> {
                    _onBoardingInit.value = true
                }
                currentVersionCode > savedVersionCode -> {
                    _onBoardingInit.value = true
                }
            }
        }
        // Update the shared preferences with the current version code
        cache.setInt(PREF_VERSION_CODE_KEY, currentVersionCode)
    }
}