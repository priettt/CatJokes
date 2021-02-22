package com.francisco.catjokes.di

import com.francisco.catjokes.list.infrastructure.CatsService
import com.francisco.catjokes.list.infrastructure.JokesService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

private const val CAT_API_BASE_URL = "https://api.thecatapi.com/v1/"
private const val DAD_JOKES_URL = "https://icanhazdadjoke.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsService(okHttpClient: OkHttpClient): CatsService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(CAT_API_BASE_URL)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
            .create(CatsService::class.java)
    }

    @Provides
    @Singleton
    fun provideJokesService(okHttpClient: OkHttpClient): JokesService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .baseUrl(DAD_JOKES_URL)
            .client(okHttpClient)
            .build()
            .create(JokesService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher