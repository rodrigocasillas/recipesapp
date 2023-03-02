package com.example.viapath.recipeslist.di

import com.example.viapath.BuildConfig
import com.example.viapath.recipeslist.data.network.RecipesListService
import com.example.viapath.recipeslist.data.repository.GetRecipesListRepositoryImpl
import com.example.viapath.recipeslist.domain.repository.GetRecipesListRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipesListModule {
    @Provides
    @Singleton
    fun providesRecipesListService(): RecipesListService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(RecipesListService::class.java)
    }

    @Provides
    @Singleton
    fun providesRecipesListRepository(
        recipesListService: RecipesListService
    ): GetRecipesListRepository {
        return GetRecipesListRepositoryImpl(recipesListService)
    }
}