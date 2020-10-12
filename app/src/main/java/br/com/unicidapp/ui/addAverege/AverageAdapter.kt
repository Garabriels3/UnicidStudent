package br.com.unicidapp.ui.addAverege

import br.com.domain.entity.DisciplineAverage
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemAverageBinding
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.extensions.adapterDataBindingCast

class AverageAdapter : BaseAdapter<DisciplineAverage>(
    R.layout.item_average, { average, view ->
        with(view.adapterDataBindingCast<ItemAverageBinding>()) {

        }
    }
) {
}