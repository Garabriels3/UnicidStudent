package br.com.unicidapp.ui.average

import br.com.domain.entity.AddAverage
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemAverageBinding
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.extensions.adapterDataBindingCast

class AverageAdapter : BaseAdapter<AddAverage>(
    R.layout.item_average, { average, view ->
        with(view.adapterDataBindingCast<ItemAverageBinding>()) {
            this.averageModel = average
        }
    }
)