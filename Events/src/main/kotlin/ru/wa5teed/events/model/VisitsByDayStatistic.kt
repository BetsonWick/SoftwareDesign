package ru.wa5teed.events.model

import java.time.LocalDate

data class VisitsByDayStatistic(val date: LocalDate, val visitsCount: Int)