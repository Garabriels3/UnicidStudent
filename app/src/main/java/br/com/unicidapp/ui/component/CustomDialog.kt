package br.com.unicidapp.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import br.com.unicidapp.R
import br.com.unicidapp.databinding.DialogFragmentErrorBinding
import br.com.unicidapp.utils.extensions.dialogDataBindingCast
import br.com.unicidapp.utils.parcelable.DialogJoinJobData

class CustomDialog : DialogFragment() {

    private val dialogJoinJobData by lazy {
        arguments?.getParcelable<DialogJoinJobData>(
            DIALOG_JOIN_JOB_EXTRA
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDialogProperties()
        return viewDataBindingFactory(inflater, container)?.root
    }

    private fun setDialogProperties() {
        dialog?.let {
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun viewDataBindingFactory(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding? {
        return if (dialogJoinJobData != null) {
            bindViewDataBinding(
                R.layout.dialog_fragment_error,
                inflater,
                container
            ).dialogDataBindingCast<DialogFragmentErrorBinding>()
                ?.apply { dialogData = dialogJoinJobData }
        } else {
            return null
        }
    }

    private fun bindViewDataBinding(
        @LayoutRes view: Int, inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, view, container, false)
    }

    companion object {
        private const val DIALOG_JOIN_JOB_EXTRA = "TYPE_EXTRA"

        fun joinJobInstance(dialogJoinJobData: DialogJoinJobData): CustomDialog {
            val arguments = Bundle()
            arguments.putParcelable(DIALOG_JOIN_JOB_EXTRA, dialogJoinJobData)
            return CustomDialog().apply {
                this.arguments = arguments

            }
        }
    }
}