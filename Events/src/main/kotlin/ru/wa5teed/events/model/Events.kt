package ru.wa5teed.events.model

data class SubscriptionEvent(
    val id: Int,
    val dateFrom: String,
    val dateTo: String,
)

data class AttendanceEvent(
    val id: Int,
    val dateTime: String,
    val enter: Boolean
)
