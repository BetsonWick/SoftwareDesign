package ru.wa5teed.vkstatistics

import org.mockito.Mockito
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import ru.wa5teed.vkstatistics.service.VkApiService

@TestConfiguration
@ActiveProfiles(value = ["test"])
class VkStatisticsTestConfig {
    @Bean
    fun vkApiService(): VkApiService = Mockito.mock(VkApiService::class.java)
}