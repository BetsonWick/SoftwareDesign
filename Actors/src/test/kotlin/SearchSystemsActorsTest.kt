import model.SearchEngine
import model.SearchSystem
import model.SerpApi
import model.TIMEOUT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class SearchSystemsActorsTest {
    val serpApi = Mockito.mock(SerpApi::class.java)
    val searchSystem = SearchSystem(serpApi)

    @Test
    fun testSearchSystem() {
        TIMEOUT = 5000L
        val response = Messages.Response.newBuilder()
            .setEngine(SearchEngine.GOOGLE.name)
            .addAllLink(listOf("a", "b"))
            .build()
        Mockito.`when`(
            serpApi.sendRequest(any(), any(), any())
        ).then {
            response
        }
            .thenReturn(
                response
            )
        assertEquals(listOf(response), searchSystem.search("query", setOf(SearchEngine.GOOGLE)))
    }
    @Test
    fun testFailSearchSystem() {
        TIMEOUT = 1L
        val response = Messages.Response.newBuilder()
            .setEngine(SearchEngine.GOOGLE.name)
            .addAllLink(listOf("a", "b"))
            .build()
        Mockito.`when`(
            serpApi.sendRequest(any(), any(), any())
        ).then {
            response
        }
            .thenReturn(
                response
            )
        assertEquals(listOf<Messages.Response>(), searchSystem.search("query", setOf(SearchEngine.GOOGLE)))
    }
}
