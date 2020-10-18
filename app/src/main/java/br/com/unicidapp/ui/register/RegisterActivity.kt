package br.com.unicidapp.ui.register

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityRegisterBinding
import br.com.unicidapp.ui.component.CustomDialog
import br.com.unicidapp.ui.component.DialogState
import br.com.unicidapp.ui.login.LoginActivity
import br.com.unicidapp.ui.optionDialog.OptionDialogFragment
import br.com.unicidapp.utils.applyDrawable
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.hideKeyboard
import br.com.unicidapp.utils.extensions.isEnabled
import br.com.unicidapp.utils.extensions.setupToolbar
import br.com.unicidapp.utils.parcelable.DialogJoinJobData
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override val viewModel: RegisterViewModel by viewModel()

    lateinit var sheet: BottomSheetDialog
    lateinit var customDialog: CustomDialog

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
    }

    override fun setupScreen() {
        setupToolbar(binding.toolbar, true)
        sheet = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
    }

    override fun subscribeUi() {
        bind(viewModel.openCourseNameSheet, ::openSchoolLevelSheet)
        bind(viewModel.enableRegisterButton, ::enableNextStepClick)
        bind(viewModel.errorDialog, ::onErrorRegister)
        bind(viewModel.userNameBorderColor, ::applyColorNameBorder)
        bind(viewModel.passwordBorderColor, ::applyColorPasswordBorder)
        bind(viewModel.emailBorderColor, ::applyColorEmailBorder)
        bind(viewModel.rgmBorderColor, ::applyColorRgmBorder)
        bind(viewModel.semesterBorderColor, ::applyColorSemesterBorder)
        bind(viewModel.courseNameBorderColor, ::applyColorCourseNameBorder)
        bind(viewModel.hideKeyboard) { hideKeyboard() }
    }

    private fun openSchoolLevelSheet(listSchoolLevel: List<SelectionItem>) {
        OptionDialogFragment(
            listSchoolLevel, "Selecione seu curso"
        ) { option ->
            viewModel.setCourseNameChanged(option)
            binding.courseName = option.find { it.isSelected }?.description
        }.also { it.show(supportFragmentManager, EMPTY_TEXT) }
    }

    private fun enableNextStepClick(isEnable: Boolean) {
        binding.btRegister.isEnabled(
            isEnable,
            R.drawable.circle_green_button,
            R.drawable.circle_shape_gray
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun applyColorEmailBorder(enable: Boolean) {
        applyDrawable(
            binding.etEmail,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorNameBorder(enable: Boolean) {
        applyDrawable(
            binding.etName,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorPasswordBorder(enable: Boolean) {
        applyDrawable(
            binding.etPassword,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorRgmBorder(enable: Boolean) {
        applyDrawable(
            binding.etRgm,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorSemesterBorder(enable: Boolean) {
        applyDrawable(
            binding.etSemester,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun applyColorCourseNameBorder(enable: Boolean) {
        applyDrawable(
            binding.tietCourse,
            if (enable) R.drawable.shape_round_corners else R.drawable.shape_round_corners_red,
            this
        )
    }

    private fun onErrorRegister(isError: Boolean) {
        if (isError) {
            supportFragmentManager.let {
                sheet.dismiss()
                customDialog = CustomDialog.joinJobInstance(
                    DialogJoinJobData(
                        resources.getString(R.string.congratulation_label),
                        DialogState.SUCCESS,
                        resources.getString(R.string.success_register_description)
                    ) { LoginActivity.start(this) }
                )
                customDialog.showNow(it, "")
            }
        } else {
            supportFragmentManager.let {
                sheet.dismiss()
                customDialog = CustomDialog.joinJobInstance(
                    DialogJoinJobData(
                        resources.getString(R.string.attention_label),
                        DialogState.ERROR,
                        resources.getString(R.string.error_register_description)
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
        private const val EMPTY_TEXT = ""

        fun start(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
}

