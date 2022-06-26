package com.atul.test.controller

import com.atul.test.services.ActorNotFoundException
import com.atul.test.services.MovieNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(value = [ActorNotFoundException::class, MovieNotFoundException::class])
    fun doHandleExceptions(ex: Exception): ResponseEntity<Body> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Body(ex.message.orEmpty()))
    }

    data class Body(val message: String)
}