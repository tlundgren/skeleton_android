package com.android.skeleton.data.feature

import com.android.skeleton.domain.Recipe
import com.squareup.moshi.FromJson

/**
 * Produces an array of [Recipe]s from a [GetRecipes].
 */
class GetRecipesAdapter {
    @FromJson
    fun getRecipes(getRecipes: GetRecipes): List<Recipe> {
        return getRecipes.hits.map { hit ->
            Recipe(hit.recipe.label)
        }
    }
}