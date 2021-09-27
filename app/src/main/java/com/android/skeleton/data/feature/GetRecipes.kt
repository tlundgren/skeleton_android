package com.android.skeleton.data.feature

/**
 * Represents the response received from ServiceRecipe's getRecipes.
 */
class GetRecipes(
    val from: Integer,
    val to: Integer,
    val count: Integer,
    val hits: List<GetRecipesHit>
)