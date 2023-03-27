package ru.wa5teed.events.controller

import org.openapitools.api.TurnstileApi
import org.openapitools.model.PassDto
import org.openapitools.model.PassResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import ru.wa5teed.events.model.Pass
import ru.wa5teed.events.service.TurnstileService
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
@RestController
class TurnstileApiService(val turnstileService: TurnstileService) : TurnstileApi {
    override fun enter(subscriptionId: Int): ResponseEntity<PassResponse> {
        val pass = turnstileService.enter(subscriptionId)
        return ResponseEntity.ok(
            PassResponse()
                .result(
                    passMapper(pass)
                )
        )
    }

    override fun leave(subscriptionId: Int): ResponseEntity<PassResponse> {
        val pass = turnstileService.leave(subscriptionId)
        return ResponseEntity.ok(
            PassResponse()
                .result(
                    passMapper(pass)
                )
        )
    }

    fun passMapper(pass: Pass): PassDto = PassDto()
        .result(pass.result)
        .dateTime(
            LocalDateTime.parse(pass.dateTime)
                .atOffset(ZoneOffset.UTC)
        )
}
