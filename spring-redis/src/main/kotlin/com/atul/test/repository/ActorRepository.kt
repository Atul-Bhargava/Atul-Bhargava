package com.atul.test.repository

import com.atul.test.model.Actor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ActorRepository : CrudRepository<Actor, String> {
}