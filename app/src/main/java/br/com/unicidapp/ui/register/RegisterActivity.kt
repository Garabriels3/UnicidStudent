package br.com.unicidapp.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityRegisterBinding
import br.com.unicidapp.ui.component.CustomDialog
import br.com.unicidapp.ui.component.DialogState
import br.com.unicidapp.ui.optionDialog.OptionDialogFragment
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.hideKeyboard
import br.com.unicidapp.utils.extensions.isEnabled
import br.com.unicidapp.utils.extensions.setupToolbar
import br.com.unicidapp.utils.parcelable.DialogJoinJobData
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    lateinit var sheet: BottomSheetDialog
    lateinit var customDialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
        setupToolbar(binding.toolbar, true)
        setupObservables()
        viewModel.onCreate()
        sheet = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
    }

    private fun setupObservables() {
        bind(viewModel.openCourseNameSheet, ::openSchoolLevelSheet)
        bind(viewModel.enableRegisterButton, ::enableNextStepClick)
        bind(viewModel.errorDialog, ::onErrorRegister)
    }

    private fun openSchoolLevelSheet(listSchoolLevel: List<SelectionItem>) {
        OptionDialogFragment(
            listSchoolLevel, "Selecione seu curso"
        ) { option ->
            viewModel.setCourseNameChanged(option)
            binding.courseName = option.find { it.isSelected }?.id
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

    private fun onErrorRegister(isError: Boolean) {
        if (isError) {
            supportFragmentManager.let {
                sheet.dismiss()
                customDialog = CustomDialog.joinJobInstance(
                    DialogJoinJobData(
                        resources.getString(R.string.error_register_description),
                        DialogState.SUCCESS,
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

