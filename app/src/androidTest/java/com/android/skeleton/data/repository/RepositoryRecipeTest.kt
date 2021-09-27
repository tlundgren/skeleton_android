package com.android.skeleton.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.skeleton.data.feature.GetRecipesAdapter
import com.android.skeleton.data.feature.ServiceRecipe
import com.android.skeleton.data.feature.serviceRecipeUrl
import com.android.skeleton.domain.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.ExpectedException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Tests [RepositoryRecipe] using a fake implementation of [ServiceRecipe].
 * The tests are only meant to illustrate how to perform a component test.
 */
class RepositoryRecipeTest {

    private lateinit var repository: RepositoryRecipe

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var exception: ExpectedException = ExpectedException.none()

    @Before
    fun pre() {
        val service = ServiceRecipeFake()
        repository = RepositoryRecipe(service)
    }

    /**
     * Verifies that the repository returns results.
     */
    @Test
    fun loadAll() = runBlocking {
        val recipes = repository.loadAll()
        Assert.assertTrue(recipes.isNotEmpty())
    }

}

class ServiceRecipeFake: ServiceRecipe {
    override suspend fun getRecipes(
        type: String,
        query: String,
        appId: String,
        appKey: String
    ): List<Recipe> {
        delay(3000)
        return listOf<Recipe>(
            Recipe("Roasted Chicken"),
            Recipe("Boiled Chicken"),
            Recipe("Grilled Chicken"),
        )
    }
}