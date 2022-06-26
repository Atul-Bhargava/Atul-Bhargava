package com.atul.test.services

import com.atul.test.controller.MovieController
import com.atul.test.model.Movie

interface MovieService {

    @Throws(MovieNotFoundException::class)
    fun getMovie(id: String): Movie

    fun getAllMovies(): List<Movie>

    @Throws(MovieNotFoundException::class)
    fun updateMovie(id: String, movieDto: MovieController.MovieDto): Movie

    fun updateMovie(movie: Movie): Movie

    fun createMovie(movieDto: MovieController.MovieDto) : Movie

    @Throws(MovieNotFoundException::class)
    fun deleteMovie(id: String)
}