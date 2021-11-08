package com.android.skeleton.analytics.event

import com.android.skeleton.analytics.Constants
import com.android.skeleton.analytics.Provider

/**
 * Event that records the creation or deletion of an "item". Records the operation and the name
 * of the item.
 */
// Events of this class are relayed to the provider here specified, namely Firebase.
class ItemOperation(
    operation: Operation,
    itemName: String
): Event(
    Constants.Events.ItemOperation.EVENT,
    mapOf(
        Constants.Events.ItemOperation.Params.OPERATION to operation.name,
        Constants.Events.ItemOperation.Params.ITEM to itemName
    ),
    listOf(Provider.FIREBASE)
) {

    enum class Operation {
        CREATE,
        DELETE
    }
}