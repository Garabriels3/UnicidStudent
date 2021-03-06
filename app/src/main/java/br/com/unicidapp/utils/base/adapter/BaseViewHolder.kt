package br.com.unicidapp.utils.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root)