package br.com.unicidapp.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityLoginBinding
import br.com.unicidapp.utils.applyDrawable
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.getContextCompactDrawable
import br.com.unicidapp.utils.extensions.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        setupObservables()
    }

    private fun setupObservables() {
        bind(viewModel.enableSignInButton, ::shouldEnableSignInButton)
        bind(viewModel.enableDrawableFieldUserName, ::applyColorLoginEmailBorder)
        bind(viewModel.enableDrawableFieldPassword, ::applyColorLoginPasswordBorder)
        bind(viewModel.hideKeyboard) { hideKeyboard() }
    }

    private fun shouldEnableSignInButton(enable: Boolean) {
        binding.btLogin.isEnabled = enable
        applyButtonLoginBackground(enable)
    }

    private fun applyButtonLoginBackground(enable: Boolean) {
        binding.btLogin.background =
            getContextCompactDrawable(if (enable) R.drawable.shape_round_corners_gradient else R.drawable.circle_shape_gray)
    }

    private fun applyColorLoginEmailBorder(enable: Boolean) {
        applyDrawable(
            binding.etEmail,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorLoginPasswordBorder(enable: Boolean) {
        applyDrawable(
            binding.etPassword,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}