package com.atul.test.services

import com.atul.test.controller.ActorController
import com.atul.test.model.Actor
import com.atul.test.model.Movie
import com.atul.test.repository.ActorRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.util.*

@Configuration

@Service
class DefaultActorService(val actorRepository: ActorRepository, val movieService: MovieService) : ActorService {
    private val logger = KotlinLogging.logger {}

    override fun getActor(id: String): Actor = actorRepository.findById(id).orElseThrow {
        MovieNotFoundException("Unable to find movie for $id id")
    }

    override fun getAllActors(): List<Actor> = actorRepository.findAll().toList()

    override fun updateActor(id: String, actorDto: ActorController.ActorDto): Actor {
        val actor = getActor(id).copy(
            firstName = actorDto.firstName,
            lastName = actorDto.lastName,
            birthDate = actorDto.birthDate
        )
        actor.id = id
        return actorRepository.save(actor)
    }

    override fun createActor(actorDto: ActorController.ActorDto): Actor {
        return actorRepository.save(Actor(actorDto.firstName, actorDto.lastName, actorDto.birthDate))
    }

    override fun deleteActor(id: String) = actorRepository.deleteById(id)

    override fun addActorToMovie(actorId: String, movieId: String): Movie {
        val movie: Movie = movieService.getMovie(movieId)
        val actor: Actor = getActor(actorId)
        (movie.actors as ArrayList).add(actor)
        return movieService.updateMovie(movie)
    }
}