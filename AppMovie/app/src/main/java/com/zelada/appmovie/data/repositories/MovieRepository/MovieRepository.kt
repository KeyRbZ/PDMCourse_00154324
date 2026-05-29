package com.zelada.appmovie.data.repositories.MovieRepository

import com.zelada.appmovie.model.Movie

interface MovieRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getMovieById(id: Int): Result<Movie>
}