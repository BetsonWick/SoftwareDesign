package model

import MasterActor
import Messages
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.PatternsCS.ask
import akka.util.Timeout
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

var TIMEOUT = 5000L

class SearchSystem(val api: SerpApi) {
    fun search(query: String, searchEngines: Set<SearchEngine>): List<Messages.Response> {
        val system = ActorSystem.create("SearchSystems")
        val actor = system.actorOf(
            Props.create(MasterActor::class.java, searchEngines, api),
            "master"
        )
        var result = listOf<Messages.Response>()
        try {
            result = ask(actor, query, Timeout.apply(TIMEOUT, TimeUnit.MILLISECONDS)).toCompletableFuture()
                .get() as List<Messages.Response>
        } catch (e: ExecutionException) {
            println("Zero responses")
        } finally {
            system.terminate()
        }
        return result
    }
}
