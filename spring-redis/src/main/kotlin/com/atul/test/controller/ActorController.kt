package com.atul.test.controller

import com.atul.test.model.Actor
import com.atul.test.model.Movie
import com.atul.test.services.DefaultActorService
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import mu.KotlinLogging
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/v1/actors")
class ActorController(val defaultActorService: DefaultActorService) {
    private val logger = KotlinLogging.logger {}

    @PostMapping(consumes = ["application/json"])
    private fun createActor(@RequestBody actor: ActorDto): Actor {
        logger.info { "Actor Service createActor: $actor" }
        return defaultActorService.createActor(actor)
    }

    @GetMapping("/{id}")
    private fun getActorById(@PathVariable id: String): Actor? {
        logger.info { "Actor Service getActorById: $id" }
        return defaultActorService.getActor(id)
    }

    @GetMapping()
    private fun getActors(): ResponseEntity<List<Actor>> {
        logger.info { "getActors:" }
        return ResponseEntity(defaultActorService.getAllActors(), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    private fun deleteActor(@PathVariable id: String) {
        logger.info { "deleteActor: $id" }
        return defaultActorService.deleteActor(id)
    }

    @PatchMapping(value = ["/{actorId}/link/{movieId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun addActorToMovie(@PathVariable actorId: String, @PathVariable movieId: String): Movie {
        logger.info { "addActorToMovie: $actorId link $movieId" }
        return defaultActorService.addActorToMovie(actorId, movieId)
    }

  data class ActorDto(
      val firstName: String,
      val lastName: String,
      @field:DateTimeFormat(pattern = "yyyy-MM-dd")
      @field:JsonDeserialize(using = LocalDateDeserializer::class)
      @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
      val birthDate: LocalDate
  )
}