package ru.wa5teed.vkstatistics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.openapitools.model.StatisticsByDayDto
import org.openapitools.model.StatisticsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import ru.wa5teed.vkstatistics.model.VkMessage
import ru.wa5teed.vkstatistics.service.StatisticsService
import ru.wa5teed.vkstatistics.service.VkApiService
import java.time.LocalDate


@SpringBootTest(
    classes = [VkStatisticsTestConfig::class]
)
@ActiveProfiles(value = ["test"])
class VkStatisticsApplicationTests {
    @Autowired
    lateinit var vkApiService: VkApiService

    @Autowired
    lateinit var statisticsService: StatisticsService

    @BeforeEach
    fun init() {
        Mockito.`when`(vkApiService.getRecentPosts(OWNER_ID, 100)).thenReturn(ALL_MESSAGES)
        Mockito.`when`(vkApiService.getRecentPosts(OWNER_ID, 1)).thenReturn(ALL_MESSAGES.subList(0, 2))
    }

    companion object {
        val OWNER_ID = 1L
        val ALL_MESSAGES = listOf(
            VkMessage(1, LocalDate.of(2022, 10, 1)),
            VkMessage(2, LocalDate.of(2022, 10, 1)),
            VkMessage(3, LocalDate.of(2022, 9, 20)),
            VkMessage(4, LocalDate.of(2022, 9, 1))
        )
    }

    @Test
    @DisplayName("Get messages posted a day before")
    fun getYesterdayMessages() {
        assertEquals(
            ResponseEntity.ok(
                StatisticsResponse().result(
                    listOf(
                        StatisticsByDayDto()
                            .date("2022-10-01")
                            .count(2)
                    )
                )
            ), statisticsService.getStatistics(OWNER_ID, 1)
        )
    }

    @Test
    @DisplayName("Get all messages posted")
    fun getAllMessages() {
        assertEquals(
            ResponseEntity.ok(
                StatisticsResponse().result(
                    listOf(
                        StatisticsByDayDto()
                            .date("2022-09-01")
                            .count(1),
                        StatisticsByDayDto()
                            .date("2022-09-20")
                            .count(1),
                        StatisticsByDayDto()
                            .date("2022-10-01")
                            .count(2)
                    )
                )
            ), statisticsService.getStatistics(OWNER_ID, 100)
        )
    }
}
