package ru.wa5teed.events.service

import com.google.gson.Gson
import org.springframework.stereotype.Service
import ru.wa5teed.events.EventsRepository
import ru.wa5teed.events.model.EventType
import ru.wa5teed.events.model.Subscription
import ru.wa5teed.events.model.SubscriptionEvent
import java.time.LocalDate

@Service
class SubscriptionsService(
    val eventsRepository: EventsRepository,
    val gson: Gson
) {
    fun getSubscription(id: Int): Subscription? {
        val latestSubscriptionEvent = eventsRepository.getEvents(
            id,
            listOf(EventType.UPDATE_SUBSCRIPTION.ordinal, EventType.NEW_SUBSCRIPTION.ordinal)
        )
            .map { gson.fromJson(it, SubscriptionEvent::class.java) }
            .maxByOrNull { it.dateTo }
        latestSubscriptionEvent ?: return null
        return Subscription(id, latestSubscriptionEvent.dateFrom, latestSubscriptionEvent.dateTo)
    }

    fun addSubscription(id: Int, dateFrom: LocalDate, dateTo: LocalDate) {
        eventsRepository.addEvent(
            id,
            EventType.NEW_SUBSCRIPTION.ordinal,
            gson.toJson(
                SubscriptionEvent(
                    id = id,
                    dateFrom = dateFrom.toString(),
                    dateTo = dateTo.toString(),
                )
            )
        )
    }
}
