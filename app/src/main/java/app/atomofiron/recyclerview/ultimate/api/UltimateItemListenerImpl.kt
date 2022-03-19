package app.atomofiron.recyclerview.ultimate.api

import app.atomofiron.recyclerview.ultimate.UltimateAdapter
import app.atomofiron.recyclerview.ultimate.data.CardItem
import app.atomofiron.recyclerview.ultimate.data.PictureItem
import app.atomofiron.recyclerview.ultimate.data.StringItem
import app.atomofiron.recyclerview.ultimate.utils.UltimateDataFactory
import app.atomofiron.recyclerview.utils.DataItem
import java.lang.IllegalArgumentException

// по-хорошему в роли реализации интерфейса должен быть делегат активити/фрагмента
class UltimateItemListenerImpl(
    private val adapter: UltimateAdapter,
) : UltimateItemListener {

    override fun onItemClick(index: Int, item: DataItem) {
        /*val replacement = when (item) {
            is StringItem -> UltimateDataFactory.createPicture(index)
            is PictureItem -> UltimateDataFactory.createCard(index)
            is CardItem -> UltimateDataFactory.createString(index)
            else -> throw IllegalArgumentException()
        }*/
        val stringItem = item as StringItem
        val replacement = StringItem(stringItem.string, !stringItem.checked)
        adapter.replaceItem(index, replacement)
    }
}