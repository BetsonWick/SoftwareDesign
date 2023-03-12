package model

import Messages
import org.json.JSONArray
import org.json.JSONObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

open class SerpApi {
    companion object {
        const val API_KEY = "e238244388ef0a831b87e126a13730f79c5600def43068617ac4b8f0c4cd6ae7"
    }

    open fun sendRequest(engine: SearchEngine, query: String, client: HttpClient): Messages.Response {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://serpapi.com/search.json?engine=${engine.name}&q=$query&api_key=$API_KEY&async=true"))
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonArray = JSONArray(JSONObject(response.body()).get("organic_results").toString())
        val linkList = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            linkList.add(jsonArray.getJSONObject(i).get("link").toString())
        }
        return Messages.Response.newBuilder()
            .setEngine(engine.name)
            .addAllLink(linkList)
            .build()
    }
}
