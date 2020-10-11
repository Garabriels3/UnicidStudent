package br.com.unicidapp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivitySplashBinding
import br.com.unicidapp.ui.login.LoginActivity
import br.com.unicidapp.ui.register.RegisterActivity
import br.com.unicidapp.utils.extensions.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel.onStartApp()
        viewModel.checkFirstRun()
        setupObservables()
    }

    private fun setupObservables() {
        bind(viewModel.goToLogin) { if (it) LoginActivity.start(this) }
        bind(viewModel.onBoardingInit) { if (it) RegisterActivity.start(this) }
    }
}