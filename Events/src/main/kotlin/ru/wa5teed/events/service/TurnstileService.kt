package ru.wa5teed.events.service

import com.google.gson.Gson
import org.springframework.stereotype.Service
import ru.wa5teed.events.EventsRepository
import ru.wa5teed.events.model.AttendanceEvent
import ru.wa5teed.events.model.EventType
import ru.wa5teed.events.model.Pass
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@Service
class TurnstileService(
    val eventsRepository: EventsRepository,
    val subscriptionsService: SubscriptionsService,
    val gson: Gson
) {
    @PostConstruct
    fun fillDatabase() {
        eventsRepository.getEvents(listOf(EventType.ENTER_GYM.ordinal, EventType.LEAVE_GYM.ordinal))
            .map {
                gson.fromJson(it, AttendanceEvent::class.java)
            }
            .forEach {
                val thisDateTime = LocalDateTime.parse(it.dateTime)
                if (it.enter) {
                    addVisit(it.id, thisDateTime)
                } else {
                    completeVisit(it.id, thisDateTime)
                }
            }
    }

    fun enter(id: Int): Pass {
        val currentDateTime = LocalDateTime.now()
        val subscription = subscriptionsService.getSubscription(id)
        var result = false
        if (subscription != null && currentDateTime.toLocalDate().toString() <= subscription.dateTo) {
            result = true
            addVisit(id, currentDateTime)
            eventsRepository.addEvent(
                id,
                EventType.ENTER_GYM.ordinal,
                gson.toJson(
                    AttendanceEvent(
                        id = id,
                        dateTime = currentDateTime.toString(),
                        enter = true
                    )
                )
            )
        }
        return Pass(id, result, currentDateTime.toString())
    }

    fun leave(id: Int): Pass {
        val currentDateTime = LocalDateTime.now()
        completeVisit(id, currentDateTime)
        eventsRepository.addEvent(
            id,
            EventType.LEAVE_GYM.ordinal,
            gson.toJson(
                AttendanceEvent(
                    id = id,
                    dateTime = currentDateTime.toString(),
                    enter = false
                )
            )
        )
        return Pass(id, true, currentDateTime.toString())
    }
}