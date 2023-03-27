package ru.wa5teed.events.controller

import org.openapitools.api.StatisticsApi
import org.openapitools.model.DayVisitsStatisticDto
import org.openapitools.model.DayVisitsStatisticsResponse
import org.openapitools.model.SummaryStatisticDto
import org.openapitools.model.SummaryStatisticResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import ru.wa5teed.events.service.getVisitSummaryStatistics
import ru.wa5teed.events.service.getVisitsByDaysStatistics

@Component
@RestController
class StatisticsApiService : StatisticsApi {
    override fun getDayVisitsStatistics(subscriptionId: Int): ResponseEntity<DayVisitsStatisticsResponse> {
        return ResponseEntity.ok(DayVisitsStatisticsResponse().result(
            getVisitsByDaysStatistics(subscriptionId).map {
                DayVisitsStatisticDto()
                    .date(it.date)
                    .visitsCount(it.visitsCount)
            }
        )
        )
    }

    override fun getSummaryStatistics(subscriptionId: Int): ResponseEntity<SummaryStatisticResponse> {
        val summaryStatisticById = getVisitSummaryStatistics(subscriptionId)
        return ResponseEntity.ok(
            SummaryStatisticResponse().result(
                listOf(
                    SummaryStatisticDto()
                        .avgDuration(summaryStatisticById.avgDuration)
                        .frequency(summaryStatisticById.frequency)
                )
            )
        )
    }
}
