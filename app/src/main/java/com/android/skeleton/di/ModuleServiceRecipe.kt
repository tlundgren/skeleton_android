package com.android.skeleton.di

import android.content.Context
import com.android.skeleton.data.feature.GetRecipesAdapter
import com.android.skeleton.data.feature.ServiceRecipe
import com.android.skeleton.data.feature.serviceRecipeUrl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dependency injection binding to tell Hilt how to instantiate a [ServiceRecipe].
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleServiceRecipe {
    @Singleton
    @Provides
    fun provideServiceRecipe(@ApplicationContext context: Context): ServiceRecipe {
        val moshi = Moshi.Builder()
            .add(GetRecipesAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(serviceRecipeUrl)
            .build()
        return retrofit.create(ServiceRecipe::class.java)
    }
}