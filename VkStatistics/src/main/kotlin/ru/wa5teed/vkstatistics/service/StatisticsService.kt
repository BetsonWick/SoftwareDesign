package ru.wa5teed.vkstatistics.service

import org.openapitools.api.StatisticsApi
import org.openapitools.model.StatisticsByDayDto
import org.openapitools.model.StatisticsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@Component
class StatisticsService : StatisticsApi {

    @Autowired
    lateinit var vkApiService: VkApiService

    override fun getStatistics(ownerId: Long, nDays: Long): ResponseEntity<StatisticsResponse> {
        val vkMessages = vkApiService.getRecentPosts(ownerId, nDays)
            .groupBy { it.date }
            .toSortedMap()
            .map {
                StatisticsByDayDto()
                    .date(it.key.toString())
                    .count(it.value.size)
            }
        return ResponseEntity.ok(StatisticsResponse().result(vkMessages))
    }
}