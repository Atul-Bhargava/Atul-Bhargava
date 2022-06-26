package com.atul.test.controller

import com.atul.test.model.Movie
import com.atul.test.repository.MovieRepository
import com.atul.test.services.MovieService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/movies")
class MovieController(val movieService: MovieService, val movieRepository: MovieRepository) {


    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    private fun createMovie(@RequestBody movie: MovieDto): Movie = movieService.createMovie(movie)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieById(@PathVariable id: String): Movie = movieService.getMovie(id)

    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateMovie(@PathVariable id: String, @RequestBody movie: MovieDto): Movie = movieService.updateMovie(id, movie)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private fun getMovies(): List<Movie> = movieService.getAllMovies()

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteMovie(@PathVariable id: String) = movieService.deleteMovie(id)

    data class MovieDto(
        val name: String?,
        val genre: String?,
        val year: Int?
    )
}