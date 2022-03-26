package app.atomofiron.recyclerview.ultimate.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import app.atomofiron.recyclerview.R
import app.atomofiron.recyclerview.ultimate.holder.StringViewHolder
import app.atomofiron.recyclerview.utils.GenericViewHolder

enum class UltimateViewHolderFactory {
    STRING {
        override fun createHolder(parent: ViewGroup): GenericViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemView = inflater.inflate(R.layout.item_string, parent, false)
            return StringViewHolder(itemView)
        }
    };

    abstract fun createHolder(parent: ViewGroup): GenericViewHolder
}