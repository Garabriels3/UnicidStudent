package br.com.unicidapp.ui.optionDialog

import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.unicidapp.databinding.FragmentOptionDialogBinding
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.disableButton
import br.com.unicidapp.utils.extensions.enableButton
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OptionDialogFragment(
    private val listOptions: List<SelectionItem>,
    private val title: String? = null,
    private val enableCheckBox: Boolean? = null,
    private val selected: (List<SelectionItem>) -> Unit
) : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.Theme_MaterialComponents_BottomSheetDialog

    private lateinit var sheetDialog: BottomSheetDialog
    private lateinit var binding: FragmentOptionDialogBinding
    private lateinit var adapterOption: OptionAdapter

    private val viewModel: OptionsViewModel by viewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        sheetDialog = BottomSheetDialog(requireContext(), theme)
        return sheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        enableCheckBox?.let {
            binding
        }
        title?.let {
            binding.tvTitle.isVisible = true
            binding.tvTitle.text = it
        }

        optionRecyclerViewFactory()
        subscribeUi()
        onOptionLoaded(listOptions)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet = it.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.peekHeight = resources.displayMetrics.heightPixels
            view?.requestLayout()
        }
    }

    private fun subscribeUi() {
        bind(viewModel.enableButton, ::enableButton)
        bind(viewModel.onConfirmOption) { onConfirmClick() }
    }


    private fun optionRecyclerViewFactory() {
        adapterOption = OptionAdapter(viewModel::onOptionSelected)
        binding.rvOptions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = adapterOption
        }
    }

    private fun onOptionLoaded(items: List<SelectionItem>) {
        adapterOption.submitList(items.toMutableList())
        enableButton(items.find { it.isSelected }?.isSelected ?: false)
    }

    private fun enableButton(isEnabled: Boolean) {
        binding.btConfirm.isEnabled
        binding.btConfirm.apply {
            this.isEnabled = isEnabled
            if (isEnabled) {
                this.enableButton()
            } else {
                this.disableButton()
            }
        }
    }

    private fun onConfirmClick() {
        viewModel.optionSelected.value?.let { selected.invoke(it) }
        context?.run { sheetDialog.dismiss() }
    }
}
