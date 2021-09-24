package com.android.skeleton.domain

import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

/**
 * An item, identified by [name].
 * Immutable.
 */
@Keep
@Parcelize
class Item(val name: String, val description: String, val position: Int): Parcelable {
    /**
     * @return true, when ids are equal.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other !is Item)
            return false
        return other.name == name
    }

    override fun toString(): String {
        return "Item {$name}, {$description}, {$position}"
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}