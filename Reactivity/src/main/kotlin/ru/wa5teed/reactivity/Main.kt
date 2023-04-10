package ru.wa5teed.reactivity

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@EnableReactiveMongoRepositories
@SpringBootApplication
open class Main : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName(): String {
        return "mongo"
    }

    @Bean
    open fun mongoClient(): MongoClient {
        return MongoClients.create()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Main::class.java, *args)
}
