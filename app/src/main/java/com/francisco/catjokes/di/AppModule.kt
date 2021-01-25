package com.francisco.catjokes.di

import com.francisco.catjokes.list.infrastructure.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideCatApiService(): CatService {
        return Retrofit.Builder()
            .baseUrl(" https://pokeapi.co/api/v2/")
            .build()
            .create(PokeApiService::class.java)
    }
}