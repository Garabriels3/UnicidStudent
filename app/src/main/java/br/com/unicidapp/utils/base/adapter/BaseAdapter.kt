package br.com.unicidapp.utils.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseAdapter<T>(
    @LayoutRes val view: Int,
    val onBind: (T, ViewDataBinding) -> Unit
) : ListAdapter<T, BaseViewHolder>(
    BaseDiffUtil<T>()
) {

    var items: MutableList<T> = mutableListOf()
        protected set

    private val firstPositionIndex = 0

    fun addItemLastPosition(item: T) {
        addItemList(item)
    }

    fun replaceItem(pair: Pair<Int, T>) {
        setItemList(pair.first, pair.second)
    }

    fun deleteItem(position: Int) {
        deleteItemList(position)
    }

    fun notifyItemChanged(item: T) {
        indexOfAction(item) { index ->
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items.getOrNull(position)?.run {
            onBind(this, holder.viewDataBinding)
            return
        }
        throw BaseAdapterException.PositionInvalidToBindViewHolderException(
            position
        )
    }

    override fun submitList(list: List<T>?) {
        if (items.isNotEmpty()) items.clear()
        list?.run(items::addAll)
        super.submitList(items)
        notifyDataSetChanged()
    }

    private fun indexOfAction(item: T, block: (Int) -> Unit) {
        items.indexOf(item).also { index ->
            if (index != -1) block.invoke(index) else throw BaseAdapterException.ItemNotFoundException(
                index
            )
        }
    }

    fun addItems(itemsList: List<T>) {
        val listSize = itemCount
        items.addAll(itemsList)
        notifyItemRangeInserted(listSize, itemsList.size)
    }

    private fun addItemList(item: T, insertFirstPosition: Boolean = false) {
        if (insertFirstPosition) {
            items.add(getFirstPosition(), item)
            notifyItemInserted(getFirstPosition())
        } else {
            items.add(item)
            notifyItemInserted(getLastPosition())
        }
    }

    private fun deleteItemList(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    private fun setItemList(index: Int, item: T) {
        items[index] = item
        notifyItemChanged(index)
    }

    private fun getLastPosition() = items.size - 1

    fun isLastPosition(index: Int): Boolean = (items.size - 1) == index

    private fun getFirstPosition() = firstPositionIndex
}