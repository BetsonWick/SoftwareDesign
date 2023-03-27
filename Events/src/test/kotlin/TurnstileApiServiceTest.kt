import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.openapitools.model.PassDto
import org.openapitools.model.PassResponse
import org.springframework.http.ResponseEntity
import ru.wa5teed.events.controller.TurnstileApiService
import ru.wa5teed.events.model.Pass
import ru.wa5teed.events.service.TurnstileService
import java.time.LocalDateTime
import java.time.ZoneOffset

class TurnstileApiServiceTest {
    companion object {
        val turnstileService: TurnstileService = Mockito.mock(TurnstileService::class.java)
        val turnstileApiService = TurnstileApiService(turnstileService)
        val pass = Pass(
            1,
            true,
            "2023-03-27T02:36:49.117934800"
        )
    }

    @Test
    fun testPass() {
        Mockito.`when`(turnstileService.enter(1)).thenReturn(pass)
        Assertions.assertEquals(
            ResponseEntity.ok(
                PassResponse().result(
                    PassDto()
                        .result(true)
                        .dateTime(LocalDateTime.parse(pass.dateTime).atOffset(ZoneOffset.UTC))
                )
            ), turnstileApiService.enter(1)
        )
    }

    @Test
    fun testLeave() {
        Mockito.`when`(turnstileService.leave(1)).thenReturn(pass)
        Assertions.assertEquals(
            ResponseEntity.ok(
                PassResponse().result(
                    PassDto()
                        .result(true)
                        .dateTime(LocalDateTime.parse(pass.dateTime).atOffset(ZoneOffset.UTC))
                )
            ), turnstileApiService.leave(1)
        )
    }
}