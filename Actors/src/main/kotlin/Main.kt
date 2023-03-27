import model.SearchEngine
import model.SearchSystem
import model.SerpApi

fun main() {
    println(SearchSystem(SerpApi()).search("Query", setOf(SearchEngine.BING, SearchEngine.GOOGLE)))
}
