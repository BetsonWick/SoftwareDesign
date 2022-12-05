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
import ru.wa5teed.vkstatistics.service.StatisticsService
import ru.wa5teed.vkstatistics.service.VkApiService
import java.nio.file.Files
import java.time.LocalDate
import java.util.*
import kotlin.io.path.toPath


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
        Mockito.`when`(vkApiService.getRecentPostsResponseString(OWNER_ID))
            .thenReturn(
                Files.readString(
                    Objects.requireNonNull(javaClass.getResource("/all.txt"))
                        .toURI().toPath()
                )
            )
    }

    companion object {
        const val OWNER_ID = 1L
    }

    @Test
    @DisplayName("Get messages posted a day before")
    fun getYesterdayMessages() {
        assertEquals(
            ResponseEntity.ok(
                StatisticsResponse().result(
                    listOf(
                        StatisticsByDayDto()
                            .date("2018-05-08")
                            .count(1),
                    )
                )
            ), statisticsService.getStatistics(OWNER_ID, 1, LocalDate.of(2018, 5, 8))
        )
    }

    @Test
    @DisplayName("Get multiple messages posted")
    fun getMultipleMessages() {
        assertEquals(
            ResponseEntity.ok(
                StatisticsResponse().result(
                    listOf(
                        StatisticsByDayDto()
                            .date("2018-04-30")
                            .count(3),
                        StatisticsByDayDto()
                            .date("2018-05-01")
                            .count(2),
                        StatisticsByDayDto()
                            .date("2018-05-03")
                            .count(1)
                    )
                )
            ), statisticsService.getStatistics(OWNER_ID, 8, LocalDate.of(2018, 5, 7))
        )
    }
}
