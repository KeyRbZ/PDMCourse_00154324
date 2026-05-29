package com.zelada.appmovie.data.repositories.MovieRepository

import com.zelada.appmovie.dummy.dummyMovies
import com.zelada.appmovie.model.Movie
import kotlinx.coroutines.delay

class MovieDummyRepository : MovieRepository {

    override suspend fun getMovies(): Result<List<Movie>> {
        delay(2000)
        return Result.success(dummyMovies)
    }

    override suspend fun getMovieById(id: Int): Result<Movie> {
        delay(5000)

        val movie = dummyMovies.find { it.id == id }

        return if (movie != null) {
            Result.success(movie)
        } else {
            Result.failure(Exception("Movie not found"))
        }
    }
}