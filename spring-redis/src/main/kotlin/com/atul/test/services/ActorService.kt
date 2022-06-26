package com.atul.test.services

import com.atul.test.controller.ActorController
import com.atul.test.model.Actor
import com.atul.test.model.Movie

interface ActorService {

    @Throws(ActorNotFoundException::class)
    fun getActor(id: String): Actor

    fun getAllActors(): List<Actor>

    @Throws(ActorNotFoundException::class)
    fun updateActor(id: String, actorDto: ActorController.ActorDto): Actor

    fun createActor(actorDto: ActorController.ActorDto): Actor

    @Throws(ActorNotFoundException::class)
    fun deleteActor(id: String)

    @Throws(ActorNotFoundException::class, MovieNotFoundException::class)
    fun addActorToMovie(actorId: String, movieId: String): Movie
}