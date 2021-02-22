package com.francisco.catjokes.list.infrastructure

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

private const val DAD_JOKES_URL = "search"

interface JokesService {
    @Headers(
        "Accept: application/json",
        "User-Agent: CatJokes (https://github.com/priettt)"
    )
    @GET
    suspend fun getJokes(
        @Url url: String = DAD_JOKES_URL,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): JokesApiResponse
}

@Serializable
data class JokesApiResponse(val results: List<JokeApiResponse>)

@Serializable
data class JokeApiResponse(val id: String, val joke: String)