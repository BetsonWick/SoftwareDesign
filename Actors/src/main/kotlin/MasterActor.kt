import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.UntypedActor
import model.SearchEngine
import model.SerpApi
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

class MasterActor(val engines: Set<SearchEngine>, val api: SerpApi) : UntypedActor() {
    private val results = mutableListOf<Messages.Response>()
    private lateinit var masterSender: ActorRef

    override fun onReceive(message: Any?) {
        when (message) {
            is String -> {
                masterSender = sender
                for (engine in engines) {
                    val child = context.actorOf(Props.create(SlaveActor::class.java, engine, api), engine.name)
                    child.tell(message, self)
                }
                context.setReceiveTimeout(Duration.create(5000L, TimeUnit.MILLISECONDS))
            }
            is Messages.Response -> {
                results.add(message)
                println(message.engine + " " + message.linkList)
                if (results.size >= engines.size) {
                    sendAndStop()
                }
            }
            else -> {
                sendAndStop()
            }
        }
    }

    private fun sendAndStop() {
        masterSender.tell(results, self)
        context.stop(self)
    }
}
