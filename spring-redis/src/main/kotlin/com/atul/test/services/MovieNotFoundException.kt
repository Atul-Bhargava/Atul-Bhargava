package com.atul.test.services

import java.lang.Exception

class MovieNotFoundException(override val message:String) : Exception(message)