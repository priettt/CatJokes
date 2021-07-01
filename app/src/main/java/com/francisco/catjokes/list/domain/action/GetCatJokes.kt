package com.francisco.catjokes.list.domain.action

import com.francisco.catjokes.di.DefaultDispatcher
import com.francisco.catjokes.list.domain.CatJoke
import com.francisco.catjokes.list.infrastructure.CatApiResponse
import com.francisco.catjokes.list.infrastructure.CatsService
import com.francisco.catjokes.list.infrastructure.JokesApiResponse
import com.francisco.catjokes.list.infrastructure.JokesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatJokes @Inject constructor(
    private val catsService: CatsService,
    private val jokesService: JokesService,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): Result<List<CatJoke>> = withContext(dispatcher) {
        val cats = async { catsService.getCats() }
        val jokes = async { jokesService.getJokes() }
        return@withContext createCatJokes(cats.await(), jokes.await())
    }

    private fun createCatJokes(cats: List<CatApiResponse>, jokes: JokesApiResponse): Result<List<CatJoke>> {
        val catJokes = cats.zip(jokes.results) { catApiResponse, jokesApiResponse ->
            CatJoke(catApiResponse.id, catApiResponse.url, jokesApiResponse.joke)
        }
        return if (catJokes.isNotEmpty()) {
            Result.success(catJokes)
        } else {
            Result.failure(Error("No cat jokes :("))
        }
    }
}
