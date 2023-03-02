package com.example.viapath.recipedetail.di

import android.app.Application
import androidx.room.Room
import com.example.viapath.BuildConfig
import com.example.viapath.recipedetail.data.datasource.RecipeDetailDataBase
import com.example.viapath.recipedetail.data.network.RecipeDetailsService
import com.example.viapath.recipedetail.data.repository.RecipeDetailsRepositoryImpl
import com.example.viapath.recipedetail.domain.repository.RecipeDetailsRepository
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
class RecipeDetailModule {
    @Provides
    @Singleton
    fun providesRecipesListService(): RecipeDetailsService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(RecipeDetailsService::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): RecipeDetailDataBase {
        return Room.databaseBuilder(
            app,
            RecipeDetailDataBase::class.java,
            RecipeDetailDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesRecipesListRepository(
        recipeDetailService: RecipeDetailsService,
        recipeDatabase: RecipeDetailDataBase
    ): RecipeDetailsRepository {
        return RecipeDetailsRepositoryImpl(
            recipeDetailService,
            recipeDatabase.recipeDao
        )
    }
}