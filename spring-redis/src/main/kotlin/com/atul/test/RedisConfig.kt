package com.atul.test

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration


@Configuration
class RedisConfig {

    @Value("\${spring.redis.host}")
    private val redisHost: String = "localhost"

    @Value("\${spring.redis.port}")
    private val redisPort = 0

    @Value("\${spring.redis.password}")
    private val password: String? = null

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = redisHost
        redisStandaloneConfiguration.port = redisPort
        if (password != null) {
            redisStandaloneConfiguration.password = RedisPassword.of(password)
        }
        val jedisClientConfiguration: JedisClientConfiguration.JedisClientConfigurationBuilder = JedisClientConfiguration.builder()
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60))
        return JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build())
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String?,Any?>? {
        val template: RedisTemplate<String?,Any?> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }
}