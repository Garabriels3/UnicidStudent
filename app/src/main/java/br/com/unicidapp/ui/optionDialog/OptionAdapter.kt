package br.com.unicidapp.ui.optionDialog

import br.com.domain.entity.SelectionItem
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemEducationBinding
import br.com.unicidapp.utils.extensions.adapterDataBindingCast
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.base.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_education.view.radio_button

class OptionAdapter(private val itemSelected: (List<SelectionItem>) -> Unit) :
    BaseAdapter<SelectionItem>(R.layout.item_education, { selectionItem, view ->
        with(view.adapterDataBindingCast<ItemEducationBinding>()) {
            this.selectionItem = selectionItem
        }
    }) {
    private var prevSelection = NEGATIVE_ONE_VALUE
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val selectionItem = items[position]
        prevSelection = items.indexOf(items.find { it.isSelected })

        holder.viewDataBinding.root.radio_button.isChecked = selectionItem.isSelected
        holder.viewDataBinding.root.radio_button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectionItem.isSelected = true

                if (prevSelection >= ZERO_VALUE) {
                    items[prevSelection].isSelected = false
                    notifyItemChanged(prevSelection)
                }
                prevSelection = position

            } else selectionItem.isSelected = false

            itemSelected.invoke(items)
        }
        super.onBindViewHolder(holder, position)
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val NEGATIVE_ONE_VALUE = -1
    }
}