package com.android.skeleton.feature.item

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.skeleton.databinding.CardItemBinding
import com.android.skeleton.domain.Item
import timber.log.Timber

/**
 * Adapts [Item]s to [RecyclerView.ViewHolder]s.
 */
class AdapterItem(
    private val listener: ListenerItem,
    private val touchHelper: ItemTouchHelper
) :
    ListAdapter<Item, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderItem.from(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderItem)
            holder.bind(getItem(position), touchHelper)
    }

    /**
     * View representing an [Item].
     */
    class ViewHolderItem private constructor(
        val binding: CardItemBinding,
        private val resources: Resources
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            /**
             * Creates a [ViewHolderItem] that forwards action requests to [listener].
             */
            fun from(parent: ViewGroup, listener: ListenerItem): ViewHolderItem {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(inflater, parent, false)
                binding.caItemEdit.setOnClickListener {
                    val item = binding.item
                    if (item != null)
                        listener.onEditClick(item)
                }
                binding.caItemDelete.setOnClickListener {
                    val item = binding.item
                    if (item != null) {
                        listener.onDeleteClick(item)
                    }
                }
                val resources = parent.context.resources
                return ViewHolderItem(binding, resources)
            }
        }

        /**
         * Updates itself to reflect [item].
         */
        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: Item, touchHelper: ItemTouchHelper) {
            binding.item = item
            binding.caItemHeader.text = item.name
            binding.caItemBody.text = item.description
            binding.caItemHandle.setOnTouchListener { view, event ->
                Timber.d("onTouchListener ${MotionEvent.actionToString(event.action)}")
                return@setOnTouchListener when (event.action == MotionEvent.ACTION_DOWN) {
                    true -> {
                        touchHelper.startDrag(this)
                        true
                    }
                    false -> false
                }
            }
            binding.executePendingBindings()
        }
    }

    /**
     * Changes in the position attribute of an [Item] do not cause diff methods to report change
     * under any circumstance.
     */
    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return (oldItem.description == newItem.description)
        }
    }
}

class ListenerItem(
    private val editListener: (item: Item) -> Unit,
    private val deleteListener: (item: Item) -> Unit
) {
    fun onEditClick(item: Item) = editListener(item)
    fun onDeleteClick(item: Item) = deleteListener(item)
}