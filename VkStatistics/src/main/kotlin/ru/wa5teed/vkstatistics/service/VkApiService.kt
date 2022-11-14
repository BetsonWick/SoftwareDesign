package ru.wa5teed.vkstatistics.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ru.wa5teed.vkstatistics.model.VkMessage
import ru.wa5teed.vkstatistics.model.VkMessageDto
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@Component
@Profile("!test")
class VkApiService {
    companion object {
        private val httpClient = HttpClient.newHttpClient()
        private val bodyHandler = HttpResponse.BodyHandlers.ofString()
        private val version = "5.131"
        private val accessToken = "3d7078be3d7078be3d7078befb3d250a4533d703d7078be5e01314c6e9f00c6d8fffa08"
        private val gson = Gson()
    }

    fun getRecentPosts(ownerId: Long, nDays: Long): List<VkMessage> {
        val response: HttpResponse<String> = httpClient.send(
            HttpRequest.newBuilder(
                URI.create("https://api.vk.com/method/wall.get?access_token=$accessToken&v=$version&owner_id=$ownerId")
            )
                .build(),
            bodyHandler
        )
        val jsonArray = gson.fromJson(response.body(), JsonObject::class.java)
            .getAsJsonObject("response")
            .getAsJsonArray("items")

        return gson.fromJson(jsonArray, Array<VkMessageDto>::class.java).map {
            VkMessage(it.id, LocalDate.ofInstant(Instant.ofEpochSecond(it.date), ZoneOffset.UTC))
        }.filter {
            it.date > LocalDate.now().minusDays(nDays)
        }
    }
}