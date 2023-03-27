import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.openapitools.model.SubscriptionDto
import org.openapitools.model.SubscriptionResponse
import org.springframework.http.ResponseEntity
import ru.wa5teed.events.controller.SubscriptionsApiService
import ru.wa5teed.events.model.Subscription
import ru.wa5teed.events.service.SubscriptionsService
import java.time.LocalDate

class SubscriptionsApiServiceTest {
    companion object {
        val subscriptionsService: SubscriptionsService = Mockito.mock(SubscriptionsService::class.java)
        val subscriptionsApiService = SubscriptionsApiService(subscriptionsService)
        val subscription = Subscription(
            1,
            "2022-01-01",
            "2022-02-01"
        )
    }

    @Test
    fun testGetSubscriptionInfo() {
        Mockito.`when`(subscriptionsService.getSubscription(1)).thenReturn(subscription)
        Assertions.assertEquals(
            ResponseEntity.ok(
                SubscriptionResponse()
                    .result(
                        SubscriptionDto()
                            .dateFrom(LocalDate.parse(subscription.dateFrom))
                            .dateTo(LocalDate.parse(subscription.dateTo))
                    )
            ), subscriptionsApiService.getSubscriptionInfo(1)
        )
    }
}