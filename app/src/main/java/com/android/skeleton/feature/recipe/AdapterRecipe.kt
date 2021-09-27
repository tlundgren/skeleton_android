package com.android.skeleton.feature.recipe

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.skeleton.databinding.CardRecipeBinding
import com.android.skeleton.domain.Recipe

/**
 * Adapts [Recipe]s to [RecyclerView.ViewHolder]s.
 */
class AdapterRecipe:
    ListAdapter<Recipe, RecyclerView.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderRecipe.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderRecipe)
            holder.bind(getItem(position))
    }

    /**
     * View representing an [Recipe].
     */
    class ViewHolderRecipe private constructor(
        val binding: CardRecipeBinding,
        private val resources: Resources
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolderRecipe {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CardRecipeBinding.inflate(inflater, parent, false)
                val resources = parent.context.resources
                return ViewHolderRecipe(binding, resources)
            }
        }

        /**
         * Updates itself to reflect [Recipe].
         */
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldRecipe: Recipe, newRecipe: Recipe): Boolean {
            return oldRecipe == newRecipe
        }

        override fun areContentsTheSame(oldRecipe: Recipe, newRecipe: Recipe): Boolean {
            return (oldRecipe.label == newRecipe.label)
        }
    }
}
