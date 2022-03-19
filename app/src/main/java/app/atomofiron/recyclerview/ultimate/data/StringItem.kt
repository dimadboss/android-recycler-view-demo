package app.atomofiron.recyclerview.ultimate.data

import app.atomofiron.recyclerview.utils.DataItem

class StringItem(
    val string: String,
    val checked: Boolean,
) : DataItem {

    override val id: Long = (string.hashCode() + checked.hashCode()).toLong()

    override fun areContentsTheSame(other: DataItem): Boolean {
        // принцип такой же, как в equals()
        return when {
            other !is StringItem -> false
            other.string != string -> false
            other.checked != checked -> false
            else -> true
        }
    }
}