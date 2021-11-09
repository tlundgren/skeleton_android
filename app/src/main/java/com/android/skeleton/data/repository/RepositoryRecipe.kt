package com.android.skeleton.data.repository

import com.android.skeleton.BuildConfig
import com.android.skeleton.data.feature.ServiceRecipe
import com.android.skeleton.domain.Recipe
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

/**
 * Read [Recipe]s.
 */
class RepositoryRecipe @Inject constructor(private val serviceRecipe: ServiceRecipe) {

    // in a real app, you may want to consider caching
    suspend fun loadAll(): List<Recipe> {
        Timber.d("RepositoryRecipe.loadAll")
        var recipes: List<Recipe> = emptyList()
        BuildConfig.appId
        try {
            recipes = serviceRecipe.getRecipes(
                "public",
                "pasta",
                BuildConfig.appId,
                BuildConfig.appKey
            )
        } catch (e: Exception) {
            Timber.d("exception $e")
        }
        return recipes
    }
}