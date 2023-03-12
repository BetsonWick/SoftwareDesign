import model.SearchEngine
import model.SearchSystem
import model.SerpApi

fun main() {
    SearchSystem(SerpApi()).search("Query", setOf(SearchEngine.BING))
}
