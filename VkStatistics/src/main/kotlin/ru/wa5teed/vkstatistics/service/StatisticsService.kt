package ru.wa5teed.vkstatistics.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.openapitools.api.StatisticsApi
import org.openapitools.model.StatisticsByDayDto
import org.openapitools.model.StatisticsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import ru.wa5teed.vkstatistics.model.VkMessage
import ru.wa5teed.vkstatistics.model.VkMessageDto
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@RestController
@Component
class StatisticsService : StatisticsApi {

    @Autowired
    lateinit var vkApiService: VkApiService

    companion object {
        val gson = Gson()
    }

    override fun getStatistics(ownerId: Long, nDays: Long, dateTo: LocalDate): ResponseEntity<StatisticsResponse> {
        val vkMessages = messageMapper(vkApiService.getRecentPostsResponseString(ownerId)).filter {
            it.date > dateTo.minusDays(nDays)
                    && it.date <= dateTo
        }
            .groupBy { it.date }
            .toSortedMap()
            .map {
                StatisticsByDayDto()
                    .date(it.key.toString())
                    .count(it.value.size)
            }
        return ResponseEntity.ok(StatisticsResponse().result(vkMessages))
    }

    fun messageMapper(body: String): List<VkMessage> {
        val jsonArray = gson.fromJson(body, JsonObject::class.java)
            .getAsJsonObject("response")
            .getAsJsonArray("items")

        return gson.fromJson(jsonArray, Array<VkMessageDto>::class.java).map {
            VkMessage(it.id, LocalDate.ofInstant(Instant.ofEpochSecond(it.date), ZoneOffset.UTC))
        }
    }
}
