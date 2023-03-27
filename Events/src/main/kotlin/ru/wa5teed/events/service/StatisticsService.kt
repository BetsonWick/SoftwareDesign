package ru.wa5teed.events.service

import ru.wa5teed.events.model.VisitSummaryStatistic
import ru.wa5teed.events.model.VisitsByDayStatistic
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

private val visitsByDaysStatistic = mutableMapOf<Int, MutableMap<LocalDate, Int>>()
private val visitsDurationsSeconds = mutableMapOf<Int, MutableList<Pair<LocalDate, Int>>>()
private val visitsEnterTimes = mutableMapOf<Int, MutableList<LocalDateTime>>()

fun addVisit(id: Int, currentDateTime: LocalDateTime) {
    val currentDate = currentDateTime.toLocalDate()
    val visitsByDayStatisticById = visitsByDaysStatistic[id] ?: mutableMapOf()
    visitsByDayStatisticById[currentDate] = (visitsByDayStatisticById[currentDate] ?: 0) + 1
    visitsByDaysStatistic[id] = visitsByDayStatisticById
    val visitsEnterTimesById = visitsEnterTimes[id] ?: mutableListOf()
    visitsEnterTimesById.add(currentDateTime)
    visitsEnterTimes[id] = visitsEnterTimesById
}

fun completeVisit(id: Int, currentDateTime: LocalDateTime) {
    val visitsDurationsSecondsById = visitsDurationsSeconds[id] ?: mutableListOf()
    val lastVisitEnterTimeById = visitsEnterTimes[id]?.last() ?: return
    visitsDurationsSecondsById.add(
        Pair(
            currentDateTime.toLocalDate(),
            (currentDateTime.toEpochSecond(ZoneOffset.UTC)
                    - lastVisitEnterTimeById.toEpochSecond(ZoneOffset.UTC)).toInt()
        )
    )
    visitsDurationsSeconds[id] = visitsDurationsSecondsById
}

fun getVisitsByDaysStatistics(id: Int): List<VisitsByDayStatistic> {
    return visitsByDaysStatistic[id]?.map { VisitsByDayStatistic(it.key, it.value) } ?: listOf()
}

fun getVisitSummaryStatistics(id: Int): VisitSummaryStatistic {
    val visitDurationSecondsById = visitsDurationsSeconds[id] ?: return VisitSummaryStatistic(0.0, 0.0)
    val avgDuration = visitDurationSecondsById.sumOf { it.second } / visitDurationSecondsById.size.toDouble()
    val maxDateVisit = visitDurationSecondsById.maxByOrNull { it.first }
    val minDateVisit = visitDurationSecondsById.minByOrNull { it.first }
    val frequency = if (maxDateVisit != null && minDateVisit != null) {
        var distance = maxDateVisit.first.toEpochDay() - minDateVisit.first.toEpochDay()
        if (distance == 0L) {
            distance = 1
        }
        visitDurationSecondsById.size.toDouble() / distance
    } else {
        0.0
    }
    return VisitSummaryStatistic(frequency, avgDuration)
}