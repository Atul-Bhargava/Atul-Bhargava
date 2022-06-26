package com.atul.test.services

import java.lang.Exception

class ActorNotFoundException(override val message: String): Exception(message)