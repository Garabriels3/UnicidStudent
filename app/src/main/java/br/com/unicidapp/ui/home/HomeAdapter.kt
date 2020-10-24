package br.com.unicidapp.ui.home

import br.com.unicidapp.R
import br.com.unicidapp.databinding.ItemCardMenuBinding
import br.com.unicidapp.parcelable.HomeMenu
import br.com.unicidapp.utils.base.adapter.BaseAdapter
import br.com.unicidapp.utils.extensions.adapterDataBindingCast

class HomeAdapter(
    private val onItemClicked: (type: HomeMenu) -> Unit
) : BaseAdapter<HomeMenu>(
    R.layout.item_card_menu, { homeMenu, view ->
        with(view.adapterDataBindingCast<ItemCardMenuBinding>()) {
            this.homeMenuData = homeMenu

            cvCard.setOnClickListener {
                onItemClicked.invoke(homeMenu)
            }
        }
    }
)