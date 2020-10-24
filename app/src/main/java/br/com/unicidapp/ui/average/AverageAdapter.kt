package br.com.unicidapp.ui.average

import android.annotation.SuppressLint
import br.com.domain.entity.AddAverage
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemAverageBinding
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.extensions.adapterDataBindingCast

@SuppressLint("ResourceAsColor")
class AverageAdapter : BaseAdapter<AddAverage>(
    R.layout.item_average, { average, view ->
        with(view.adapterDataBindingCast<ItemAverageBinding>()) {
            this.averageModel = average

            if (average.isAff.equals("true")) {
                llAverage.setBackgroundColor(R.color.flushOrange)
            }
        }
    }
)