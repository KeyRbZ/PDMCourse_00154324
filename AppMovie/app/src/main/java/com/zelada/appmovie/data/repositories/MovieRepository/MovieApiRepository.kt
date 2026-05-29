package com.zelada.appmovie.data.repositories.MovieRepository

import com.zelada.appmovie.data.api.Movies.KtorClient
import com.zelada.appmovie.data.api.Movies.GetMoviesResponseDto
import com.zelada.appmovie.data.api.Movies.MovieDto
import com.zelada.appmovie.data.api.Movies.toModel
import com.zelada.appmovie.model.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiRepository : MovieRepository {
    override suspend fun getMovies(): Result<List<Movie>> {
        try {
            val response: GetMoviesResponseDto = KtorClient.client.get("movie/popular") {
                parameter("language", "es-ES")
                parameter("page", 1)
            }.body()

            return Result.success(response.results.map { movieDto -> movieDto.toModel() })
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getMovieById(id: Int): Result<Movie> {
        try {
            val response: MovieDto = KtorClient.client.get("movie/$id") {
                parameter("language", "es-ES")
            }.body()

            return Result.success(response.toModel())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
