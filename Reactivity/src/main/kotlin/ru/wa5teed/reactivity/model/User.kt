package ru.wa5teed.reactivity.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
data class User(@Id val id: Long, val itemCurrency: Currency)
