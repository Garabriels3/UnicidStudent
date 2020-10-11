package br.com.unicidapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityLoginBinding
import br.com.unicidapp.ui.component.CustomDialog
import br.com.unicidapp.ui.component.DialogState
import br.com.unicidapp.ui.register.RegisterActivity
import br.com.unicidapp.utils.applyDrawable
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.hideKeyboard
import br.com.unicidapp.utils.extensions.isEnabled
import br.com.unicidapp.utils.extensions.nullableCast
import br.com.unicidapp.utils.parcelable.DialogJoinJobData
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    override val viewModel: LoginViewModel by viewModel()
    lateinit var sheet: BottomSheetDialog
    lateinit var customDialog: CustomDialog

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
    }

    override fun setupScreen() {
        sheet = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
    }

    override fun subscribeUi() {
        bind(viewModel.enableSignInButton, ::shouldEnableSignInButton)
        bind(viewModel.enableDrawableFieldUserName, ::applyColorLoginEmailBorder)
        bind(viewModel.enableDrawableFieldPassword, ::applyColorLoginPasswordBorder)
        bind(viewModel.onStartRegister) { RegisterActivity.start(this) }
        bind(viewModel.hideKeyboard) { hideKeyboard() }
        bind(viewModel.onErrorDialog, ::onErrorLogin)
    }

    private fun shouldEnableSignInButton(enable: Boolean) {
        binding.btLogin.isEnabled = enable
        applyButtonLoginBackground(enable)
    }

    private fun applyButtonLoginBackground(enable: Boolean) {
        binding.btLogin.isEnabled(
            enable,
            R.drawable.shape_round_corners_gradient,
            R.drawable.circle_shape_gray
        )
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

    private fun onErrorLogin(isError: Boolean) {
        if (isError) {
            supportFragmentManager.let {
                sheet.dismiss()
                customDialog = CustomDialog.joinJobInstance(
                    DialogJoinJobData(
                        resources.getString(R.string.attention_label),
                        DialogState.SUCCESS,
                        resources.getString(R.string.error_login_description)
                    ) { closeDialog(customDialog) }
                )
                customDialog.showNow(it, "")
            }
        }
    }

    private fun closeDialog(dialog: CustomDialog) {
        dialog.dismiss()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}