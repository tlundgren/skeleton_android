package com.android.skeleton.data.feature

import com.android.skeleton.domain.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

const val serviceRecipeUrl = "https://api.edamam.com/"

/**
 * Interface to access edamam api.
 */
interface ServiceRecipe {
    @GET("api/recipes/v2")
    suspend fun getRecipes(
        @Query("type") type: String,
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
    ): List<Recipe>
}