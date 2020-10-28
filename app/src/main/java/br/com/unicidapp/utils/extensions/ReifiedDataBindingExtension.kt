package br.com.unicidapp.utils.extensions

import androidx.databinding.ViewDataBinding
import br.com.unicidapp.ui.component.NotFoundClassDialogDataBindingGeneratedException
import br.com.unicidapp.utils.base.adapter.BaseAdapterException

inline fun <reified CLASS_DATA_BINDING_GENERATED> ViewDataBinding.adapterDataBindingCast(): CLASS_DATA_BINDING_GENERATED {
    return this as? CLASS_DATA_BINDING_GENERATED
        ?: throw BaseAdapterException.NotFoundClassDataBindingGeneratedException("${CLASS_DATA_BINDING_GENERATED::class.java}")
}
inline fun <reified CLASS_DATA_BINDING_GENERATED> ViewDataBinding.dialogDataBindingCast(): CLASS_DATA_BINDING_GENERATED? {
    return this as? CLASS_DATA_BINDING_GENERATED
        ?: throw NotFoundClassDialogDataBindingGeneratedException(
            "Class Not Found to cast: ${CLASS_DATA_BINDING_GENERATED::class.java}"
        )
}