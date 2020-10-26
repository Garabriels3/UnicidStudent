package br.com.unicidapp.ui.average

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
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

            average.afState?.let {
                if (it) {
                    llAverage.setBackgroundColor(
                        ContextCompat.getColor(
                            llAverage.context,
                            R.color.flushOrange
                        )
                    )
                }
            }
        }
    }
)