package ru.wa5teed.vkstatistics.service

import com.google.gson.Gson
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
@Profile("!test")
class VkApiService {
    companion object {
        private val httpClient = HttpClient.newHttpClient()
        private val bodyHandler = HttpResponse.BodyHandlers.ofString()
        private val version = "5.131"
        private val accessToken = "3d7078be3d7078be3d7078befb3d250a4533d703d7078be5e01314c6e9f00c6d8fffa08"
    }

    fun getRecentPostsResponseString(ownerId: Long): String {
        return httpClient.send(
            HttpRequest.newBuilder(
                URI.create("https://api.vk.com/method/wall.get?access_token=$accessToken&v=$version&owner_id=$ownerId")
            )
                .build(),
            bodyHandler
        ).body()

    }
}