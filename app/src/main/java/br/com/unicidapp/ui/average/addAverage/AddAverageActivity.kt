package br.com.unicidapp.ui.average.addAverage

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAddAverageBinding
import br.com.unicidapp.ui.optionDialog.OptionDialogFragment
import br.com.unicidapp.utils.DefaultMasks
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.hideKeyboard
import br.com.unicidapp.utils.extensions.setupToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAverageActivity : BaseActivity() {

    override val viewModel: AddAverageViewModel by viewModel()
    private lateinit var binding: ActivityAddAverageBinding

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_average)
        binding.viewModel = viewModel
        setupMask()
    }

    override fun setupScreen() {
        setupToolbar(binding.toolbar, showHome = true, title = getString(R.string.sum_average))
    }

    override fun subscribeUi() {
        bind(viewModel.openDisciplineNameSheet, ::openDisciplineSheet)
        bind(viewModel.hideKeyboard) { hideKeyboard() }
        bind(viewModel.finishActivity) { if (it) finishActivity() }
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

    private fun setupMask() {
        binding.etA1.addTextChangedListener(
            DefaultMasks.insert(
                binding.etA1,
                DefaultMasks.DECIMAL_MASK
            )
        )
        binding.etA2.addTextChangedListener(
            DefaultMasks.insert(
                binding.etA2,
                DefaultMasks.DECIMAL_MASK
            )
        )
    }

    private fun openDisciplineSheet(listDiscipline: List<SelectionItem>) {
        OptionDialogFragment(
            listDiscipline, "Selecione a disciplina"
        ) { option ->
            viewModel.setCourseNameChanged(option)
            binding.disciplineName = option.find { it.isSelected }?.description
        }.also { it.show(supportFragmentManager, EMPTY_TEXT) }
    }

    private fun finishActivity() {
        finish()
    }

    companion object {
        private const val EMPTY_TEXT = ""
        private const val EXTRA_AVERAGE = "extra_average"

        fun start(activity: Activity?, requestCode: Int) {
            val intent = Intent(activity, AddAverageActivity::class.java)
            activity?.startActivityForResult(intent, requestCode)
        }
    }
}