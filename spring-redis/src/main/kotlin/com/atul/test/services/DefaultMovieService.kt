package com.atul.test.services

import com.atul.test.controller.MovieController
import com.atul.test.model.Movie
import com.atul.test.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
@Configuration

@Service
class DefaultMovieService(val movieRepository: MovieRepository) : MovieService {

    override fun getMovie(id: String): Movie = movieRepository.findById(id).orElseThrow {
        MovieNotFoundException("Unable to find movie for $id id")
    }

    override fun getAllMovies(): List<Movie> = movieRepository.findAll().toList()

    override fun updateMovie(id: String, movieDto: MovieController.MovieDto): Movie {
        val movie: Movie = movieRepository.findById(id).orElseThrow { MovieNotFoundException("Unable to find movie for $id id") }
        val updatedMovie = movie.copy(name = movieDto.name.orEmpty(), genre = movieDto.genre.orEmpty(), year = movieDto.year)
        updatedMovie.id = movie.id
        return movieRepository.save(updatedMovie)
    }

    override fun updateMovie(movie: Movie): Movie = movieRepository.save(movie)

    override fun createMovie(movieDto: MovieController.MovieDto): Movie {
        return movieRepository.save(Movie(name = movieDto.name.orEmpty(), genre = movieDto.genre.orEmpty(), year = movieDto.year))
    }

    override fun deleteMovie(id: String) = movieRepository.delete(getMovie(id))
}