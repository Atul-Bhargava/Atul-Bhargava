package com.atul.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("com.atul.test.*"))
@EntityScan("com.atul.test.*")
class RedisSpringDataApplication

fun main(args: Array<String>) {
    runApplication<RedisSpringDataApplication>(*args)
}