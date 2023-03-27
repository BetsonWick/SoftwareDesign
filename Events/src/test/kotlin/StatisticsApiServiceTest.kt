import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openapitools.model.DayVisitsStatisticDto
import org.openapitools.model.DayVisitsStatisticsResponse
import org.openapitools.model.SummaryStatisticDto
import org.openapitools.model.SummaryStatisticResponse
import org.springframework.http.ResponseEntity
import ru.wa5teed.events.controller.StatisticsApiService
import ru.wa5teed.events.service.addVisit
import ru.wa5teed.events.service.completeVisit
import java.time.LocalDateTime

class StatisticsApiServiceTest {
    companion object {
        val statisticsApiService = StatisticsApiService()
        val dateFrom = LocalDateTime.parse("2023-03-27T02:36:49.117934800")
        val dateTo = LocalDateTime.parse("2023-03-27T05:36:49.117934800")

        init {
            addVisit(1, dateFrom)
            completeVisit(1, dateTo)
        }
    }

    @Test
    fun testSummary() {
        Assertions.assertEquals(
            ResponseEntity.ok(
                SummaryStatisticResponse().result(
                    listOf(
                        SummaryStatisticDto()
                            .frequency(1.0).avgDuration(10800.0)
                    )
                )
            ), statisticsApiService.getSummaryStatistics(1)
        )
    }

    @Test
    fun testVisitsByDays() {
        Assertions.assertEquals(
            ResponseEntity.ok(
                DayVisitsStatisticsResponse()
                    .result(
                        mutableListOf(
                            DayVisitsStatisticDto()
                                .date(dateFrom.toLocalDate())
                                .visitsCount(1)
                        )
                    )
            ), statisticsApiService.getDayVisitsStatistics(1)
        )
    }
}