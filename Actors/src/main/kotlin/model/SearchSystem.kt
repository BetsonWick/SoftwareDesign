package model

import MasterActor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.PatternsCS.ask

import akka.util.Timeout
import java.util.concurrent.TimeUnit

class SearchSystem(val api: SerpApi) {
    fun search(query: String, searchEngines: Set<SearchEngine>) : List<Messages.Response> {
        val system = ActorSystem.create("SearchSystems")
        val actor = system.actorOf(
            Props.create(MasterActor::class.java, searchEngines, api),
            "master"
        )
        val result = ask(actor, query, Timeout.apply(15, TimeUnit.SECONDS)).toCompletableFuture().get() as List<Messages.Response>
        system.terminate()
        return result
    }
}
