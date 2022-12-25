package statistic

import java.time.Clock
import java.time.Instant
import java.util.concurrent.TimeUnit

class EventStatisticImpl(private val clock: Clock) : EventStatistic {
    private val eventDeque = ArrayDeque<Pair<String, Instant>>()
    private val eventCountMap = mutableMapOf<String, Int>()

    override fun incEvent(name: String) {
        eventDeque.add(Pair(name, clock.instant()))
        eventCountMap[name] = (eventCountMap[name] ?: 0) + 1
    }

    override fun getEventStatisticByName(name: String): Double {
        actualize()
        return toEventsPerMinute(eventCountMap[name] ?: 0)
    }

    override fun getAllEventStatistic(): List<Pair<String, Double>> {
        actualize()
        return eventCountMap.map { Pair(it.key, toEventsPerMinute(it.value)) }
    }

    override fun printStatistic() {
        println(getAllEventStatistic())
    }

    private fun actualize() {
        val hourBefore = clock.instant().minusMillis(TimeUnit.HOURS.toMillis(1))
        while (eventDeque.firstOrNull()?.second?.isBefore(hourBefore) == true) {
            val removedName = eventDeque.removeFirstOrNull()?.first ?: break
            eventCountMap[removedName] = (eventCountMap[removedName] ?: 0) - 1
            if (eventCountMap[removedName] == 0) {
                eventCountMap.remove(removedName)
            }
        }
    }

    private fun toEventsPerMinute(eventsPerHour: Int): Double {
        return eventsPerHour / TimeUnit.HOURS.toMinutes(1).toDouble()
    }
}
