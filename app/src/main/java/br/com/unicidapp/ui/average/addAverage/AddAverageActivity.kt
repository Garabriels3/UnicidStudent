package br.com.unicidapp.ui.average.addAverage

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.AddAverage
import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAddAverageBinding
import br.com.unicidapp.ui.optionDialog.OptionDialogFragment
import br.com.unicidapp.utils.DefaultMasks
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAverageActivity : BaseActivity() {

    override val viewModel: AddAverageViewModel by viewModel()
    private lateinit var binding: ActivityAddAverageBinding

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_average)
        binding.viewModel = viewModel
        setupMask()

        (intent.extras?.getSerializable(EXTRA_AVERAGE) as? AddAverage)?.let {
            binding.tietCourse.isEnabled = false
            binding.disciplineName = it.discipline

            binding.etA1.apply {
                setText(it.a1)
                isEnabled = false
            }
            binding.etA2.apply {
                setText(it.a2)
                isEnabled = false
            }

            when {
                it.afState == true -> {
                    binding.etAf.shouldShowView(true)
                }
                it.reproveState == true -> {
                    binding.etAf.apply {
                        shouldShowView(true)
                        setText(it.afNote)
                        isEnabled = false
                    }
                    binding.cvShowFinalNote.shouldShowView(true)
                    binding.cvShowFinalNote.setBackgroundResource(R.drawable.shape_round_corners_red_solid)
                    binding.tvFinalNote.text = it.totalNote
                    binding.tvFinalNote.setTextColor(resources.getColor(R.color.red))
                    binding.tvState.text = REPROVE
                    binding.tvState.setTextColor(resources.getColor(R.color.red))
                }
                it.approveState == true -> {
                    binding.cvShowFinalNote.shouldShowView(true)
                    binding.cvShowFinalNote.setBackgroundResource(R.drawable.shape_round_corners)
                    binding.tvFinalNote.text = it.totalNote
                    binding.tvFinalNote.setTextColor(resources.getColor(R.color.cerulean))
                    binding.tvState.text = APPROVE
                    binding.tvState.setTextColor(resources.getColor(R.color.cerulean))
                }
            }

            viewModel.calculateHighestGrade(it)
            binding.addAverageModel = it
        }
    }

    override fun setupScreen() {
        setupToolbar(binding.toolbar, showHome = true, title = getString(R.string.sum_average))
    }

    override fun subscribeUi() {
        bind(viewModel.openDisciplineNameSheet, ::openDisciplineSheet)
        bind(viewModel.hideKeyboard) { hideKeyboard() }
        bind(viewModel.finishActivity) { if (it) finishActivity() }
        bind(viewModel.enableSaveButton, ::shouldEnableSignInButton)
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

    private fun shouldEnableSignInButton(enable: Boolean) {
        binding.btSaveNote.isEnabled = enable
        binding.btSaveAf.isEnabled = enable
        applyButtonLoginBackground(enable)
    }

    private fun applyButtonLoginBackground(enable: Boolean) {
        binding.btSaveNote.isEnabled(
            enable,
            R.drawable.circle_green_button,
            R.drawable.circle_shape_gray
        )

        binding.btSaveAf.isEnabled(
            enable,
            R.drawable.circle_green_button,
            R.drawable.circle_shape_gray
        )
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
        binding.etAf.addTextChangedListener(
            DefaultMasks.insert(
                binding.etAf,
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
        private const val REPROVE = "Reprovado!"
        private const val APPROVE = "Aprovado!"

        fun start(activity: Activity?, requestCode: Int, addAverageDetails: AddAverage? = null) {
            val intent = Intent(activity, AddAverageActivity::class.java).apply {
                putExtra(EXTRA_AVERAGE, addAverageDetails)
            }
            activity?.startActivityForResult(intent, requestCode)
        }
    }
}