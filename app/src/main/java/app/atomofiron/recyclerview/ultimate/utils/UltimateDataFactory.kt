package app.atomofiron.recyclerview.ultimate.utils

import app.atomofiron.recyclerview.ultimate.data.StringItem
import app.atomofiron.recyclerview.utils.DataItem
import app.atomofiron.recyclerview.utils.RandomFactory

class UltimateDataFactory {
    companion object {
        fun createString(index: Int): StringItem = StringItem("Item $index", true)
    }

    private val itemsFactory = RandomFactory(UltimateViewHolderFactory.values())

    fun getItems(): MutableList<DataItem> = (0..20).map { next(it) }.toMutableList()

    private fun next(index: Int): DataItem {
        return createString(index)
    }
}