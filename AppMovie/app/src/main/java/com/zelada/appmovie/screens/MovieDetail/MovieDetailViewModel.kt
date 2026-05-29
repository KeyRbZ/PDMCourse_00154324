package com.zelada.appmovie.screens.MovieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelada.appmovie.data.repositories.MovieRepository.MovieApiRepository
import com.zelada.appmovie.data.repositories.MovieRepository.MovieRepository
import com.zelada.appmovie.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val movieRepository: MovieRepository = MovieApiRepository()

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie = _movie.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading.asStateFlow()

    fun loadMovieById(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            movieRepository.getMovieById(id)
                .onSuccess { movie ->
                    _movie.value = movie
                }
            _loading.value = false
        }
    }
}