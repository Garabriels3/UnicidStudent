package br.com.unicidapp.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityRegisterBinding
import br.com.unicidapp.ui.optionDialog.OptionDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel
    }

    private fun openSchoolLevelSheet(listSchoolLevel: List<SelectionItem>) {
        OptionDialogFragment(
            listSchoolLevel, "Selecione seu curso"
        ) { option ->
            viewModel.setCourseNameChanged(option)
            binding.courseName = option.find { it.isSelected }?.description
        }.also { it.show(supportFragmentManager, EMPTY_TEXT) }
    }

    companion object {
        private const val EMPTY_TEXT = ""
    }
}