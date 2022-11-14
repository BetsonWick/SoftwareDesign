package ru.wa5teed.vkstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VkStatisticsApplication

fun main(args: Array<String>) {
    runApplication<VkStatisticsApplication>(*args)
}
