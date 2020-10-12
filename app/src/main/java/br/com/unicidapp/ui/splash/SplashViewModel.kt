package br.com.unicidapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.domain.storange.Cache
import br.com.unicidapp.BuildConfig
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.livedata.FlexibleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashViewModel(
    private val cache: Cache
) : BaseViewModel() {

    val goToLogin: LiveData<Boolean> get() = _goToLogin
    val normalInit: LiveData<Boolean> get() = _normalInit
    val onBoardingInit: LiveData<Boolean> get() = _onBoardingInit

    private val _goToLogin: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _normalInit: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)
    private val _onBoardingInit: FlexibleLiveData<Boolean> = FlexibleLiveData.default(false)

    private val delayMillsToDurationSplashScreen = 5000L

    fun onStartApp() {
        launch(baseLoading) {
            delay(delayMillsToDurationSplashScreen)
            _goToLogin.value = true
        }
    }

    fun checkFirstRun() {
        val PREF_VERSION_CODE_KEY = "version_code"
        val DOESNT_EXIST = -1

        // Get current version code
        val currentVersionCode: Int = BuildConfig.VERSION_CODE

        val savedVersionCode = cache.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        // Check for first run or upgrade
        when {
            currentVersionCode == savedVersionCode -> {
                _normalInit.value = true
                return
            }
            savedVersionCode == DOESNT_EXIST -> {
                _onBoardingInit.value = true
            }
            currentVersionCode > savedVersionCode -> {
                _onBoardingInit.value = true
            }
        }

        // Update the shared preferences with the current version code
        cache.setInt(PREF_VERSION_CODE_KEY, currentVersionCode)
    }
}