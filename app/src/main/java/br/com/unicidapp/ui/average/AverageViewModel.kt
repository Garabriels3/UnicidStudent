package br.com.unicidapp.ui.average

import androidx.lifecycle.LiveData
import br.com.unicidapp.utils.base.BaseViewModel
import br.com.unicidapp.utils.extensions.trigger
import br.com.unicidapp.utils.livedata.FlexibleLiveData

class AverageViewModel : BaseViewModel() {

    val goToAddAverage: LiveData<Boolean> get() = _goToAddAverage

    private val _goToAddAverage: FlexibleLiveData<Boolean> = FlexibleLiveData()

    fun goToAddAverageScreen() {
        _goToAddAverage.trigger()
    }
}