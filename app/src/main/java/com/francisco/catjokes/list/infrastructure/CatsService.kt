package com.francisco.catjokes.list.infrastructure

import com.francisco.catjokes.BuildConfig
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatsService {
    @Headers("x-api-key: ${BuildConfig.CATS_API_KEY}")
    @GET("images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 20,
        @Query("mime_types") mimeTypes: String = "png,jpg",
        @Query("size") size: String = "thumb",
    ): List<CatApiResponse>
}

@Serializable
data class CatApiResponse(val id: String, val url: String)