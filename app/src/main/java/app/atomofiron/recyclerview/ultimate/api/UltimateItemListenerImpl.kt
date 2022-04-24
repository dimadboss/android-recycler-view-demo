package app.atomofiron.recyclerview.ultimate.api

import app.atomofiron.recyclerview.ultimate.UltimateAdapter
import app.atomofiron.recyclerview.ultimate.data.StringItem
import app.atomofiron.recyclerview.utils.DataItem

// по-хорошему в роли реализации интерфейса должен быть делегат активити/фрагмента
class UltimateItemListenerImpl(
    private val adapter: UltimateAdapter,
) : UltimateItemListener {

    override fun onItemClick(index: Int, item: DataItem) {
        val stringItem = item as StringItem
        val replacement = StringItem(stringItem.string, !stringItem.checked, stringItem.details)
        adapter.replaceItem(index, replacement)
    }

    override fun onRemoveClick(index: Int) {
        adapter.removeItem(index)
    }
}