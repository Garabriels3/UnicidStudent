package br.com.unicidapp.ui.splash

import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivitySplashBinding
import br.com.unicidapp.ui.login.LoginActivity
import br.com.unicidapp.ui.register.RegisterActivity
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    override val viewModel: SplashViewModel by viewModel()

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun setupScreen() {}

    override fun subscribeUi() {
        bind(viewModel.goToLogin) { if (it) LoginActivity.start(this) }
        bind(viewModel.onBoardingInit) { if (it) RegisterActivity.start(this) }
    }
}