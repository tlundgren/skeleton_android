package com.android.skeleton.analytics

/**
 * Definition of the events and properties in the analytics domain.
 * This helps us keep our analytics data conforming to a controlled structure.
 */
object Constants {
    object Events {
        object ItemOperation {
            const val EVENT = "item_operation"
            object Params {
                const val OPERATION = "operation"
                const val ITEM = "item"
            }
        }
    }
}