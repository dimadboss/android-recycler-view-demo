package app.atomofiron.recyclerview.ultimate.data

import app.atomofiron.recyclerview.utils.DataItem

class StringItem(
    val string: String,
    val checked: Boolean,
    val details: String,
) : DataItem {

    override val id: Long =
        ((string + details).hashCode() xor (if (checked) 1151 else 7829)).toLong()

    override fun areContentsTheSame(other: DataItem): Boolean {
        // принцип такой же, как в equals()
        return when {
            other !is StringItem -> false
            other.string != string -> false
            other.checked != checked -> false
            other.details != details -> false
            else -> true
        }
    }
}