package br.com.unicidapp.ui.average

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import br.com.domain.entity.AddAverage
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemAverageBinding
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.extensions.adapterDataBindingCast

@SuppressLint("ResourceAsColor")
class AverageAdapter(
    onItemClicked: (average: AddAverage) -> Unit
) : BaseAdapter<AddAverage>(
    R.layout.item_average, { average, view ->
        with(view.adapterDataBindingCast<ItemAverageBinding>()) {
            this.averageModel = average

            cvAdd.setOnClickListener {
                onItemClicked.invoke(average)
            }

            when {
                average.afState == true -> {
                    tvAverage.setTextColor(
                        ContextCompat.getColor(
                            tvAverage.context,
                            R.color.white
                        )
                    )
                    tvAverageDescription.setTextColor(
                        ContextCompat.getColor(
                            tvAverageDescription.context,
                            R.color.white
                        )
                    )
                    llAverage.setBackgroundColor(
                        ContextCompat.getColor(
                            llAverage.context,
                            R.color.flushOrange
                        )
                    )
                }
                average.approveState == true -> {
                    tvAverage.setTextColor(
                        ContextCompat.getColor(
                            tvAverage.context,
                            R.color.white
                        )
                    )
                    tvAverageDescription.setTextColor(
                        ContextCompat.getColor(
                            tvAverageDescription.context,
                            R.color.white
                        )
                    )
                    llAverage.setBackgroundColor(
                        ContextCompat.getColor(
                            llAverage.context,
                            R.color.cerulean
                        )
                    )
                }
                average.reproveState == true -> {
                    tvAverage.setTextColor(
                        ContextCompat.getColor(
                            tvAverage.context,
                            R.color.white
                        )
                    )
                    tvAverageDescription.setTextColor(
                        ContextCompat.getColor(
                            tvAverageDescription.context,
                            R.color.white
                        )
                    )
                    llAverage.setBackgroundColor(
                        ContextCompat.getColor(
                            llAverage.context,
                            R.color.red
                        )
                    )
                }
            }
        }
    }
)