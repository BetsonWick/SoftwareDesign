import akka.actor.UntypedActor
import model.SearchEngine
import model.SerpApi
import java.net.http.HttpClient

class SlaveActor(val engine: SearchEngine, val api: SerpApi) : UntypedActor() {
    val client = HttpClient.newBuilder().build()
    override fun onReceive(message: Any?) {
        if (message is String) {
            sender.tell(api.sendRequest(engine, message, client), self)
            context.stop(self)
        } else {
            throw IllegalArgumentException()
        }
    }
}
