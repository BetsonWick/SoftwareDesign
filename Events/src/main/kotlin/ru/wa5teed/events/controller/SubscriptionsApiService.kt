package ru.wa5teed.events.controller

import org.openapitools.api.SubscriptionsApi
import org.openapitools.model.SubscriptionDto
import org.openapitools.model.SubscriptionResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import ru.wa5teed.events.service.SubscriptionsService
import java.time.LocalDate

@Component
@RestController
class SubscriptionsApiService(val subscriptionsService: SubscriptionsService) : SubscriptionsApi {
    override fun getSubscriptionInfo(subscriptionId: Int): ResponseEntity<SubscriptionResponse> {
        val subscription = subscriptionsService.getSubscription(subscriptionId)
        subscription ?: return ResponseEntity.ok(SubscriptionResponse())
        return ResponseEntity.ok(
            SubscriptionResponse()
                .result(
                    SubscriptionDto()
                        .dateFrom(LocalDate.parse(subscription.dateFrom))
                        .dateTo(LocalDate.parse(subscription.dateTo))
                )
        )
    }

    override fun addSubscription(
        subscriptionId: Int,
        dateFrom: LocalDate,
        dateTo: LocalDate
    ): ResponseEntity<SubscriptionResponse> {
        subscriptionsService.addSubscription(subscriptionId, dateFrom, dateTo)
        return ResponseEntity.ok(null)
    }

    override fun updateSubscription(subscriptionId: Int, duration: Long): ResponseEntity<SubscriptionResponse> {
        val currentDate = LocalDate.now()
        subscriptionsService.addSubscription(subscriptionId, currentDate, currentDate.plusDays(duration))
        return ResponseEntity.ok(null)
    }
}
