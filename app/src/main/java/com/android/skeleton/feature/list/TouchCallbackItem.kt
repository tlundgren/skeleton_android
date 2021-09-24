package com.android.skeleton.feature.list

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import timber.log.Timber

/**
 * Implements dragging functionality for the UI, and makes a callback so that any real action
 * associated with the dragging takes place.
 */
class TouchCallbackItem(
    private val swap: (from: Int, to: Int) -> Unit,
): ItemTouchHelper.Callback() {
    private val flagsDrag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
    private val flagsSwipe = 0
    private var cardViewDrag:MaterialCardView? = null

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        Timber.d("getMovementFlags")
        return makeMovementFlags(flagsDrag, flagsSwipe)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Timber.d("onMove")
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition
        swap(from, to)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Timber.d("onSwiped")
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        //Timber.d("onSelectedChanged")
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG && viewHolder != null) {
            Timber.d("onSelectedChanged drag")
            val cardView = viewHolder.itemView as MaterialCardView
            cardView.isDragged = true
            cardViewDrag = cardView
        } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE && cardViewDrag != null) {
            Timber.d("onSelectedChanged idle")
            cardViewDrag?.isDragged = false
            cardViewDrag = null
        }
    }
}