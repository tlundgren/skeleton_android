package com.android.skeleton.data.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.android.skeleton.di.ModuleServiceRecipe
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Tests [ServiceRecipeTest]. The tests are only meant to illustrate how to perform a component test.
 * Consider if these tests should run against a test server.
 */
class ServiceRecipeTest {
    private lateinit var service: ServiceRecipe

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var exception: ExpectedException = ExpectedException.none()

    @Before
    fun pre() {
        service = ModuleServiceRecipe.provideServiceRecipe(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
    }

    /**
     * Verifies that the service returns results.
     */
    @Test
    fun getRecipes() = runBlocking {
        val recipes = service.getRecipes(
            "public",
            "pasta",
            "myAppId",
            "myAppKey"
        )
        Assert.assertTrue(recipes.isNotEmpty())
    }
}