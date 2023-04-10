package ru.wa5teed.reactivity.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Item")
data class Item(@Id val id: Long, var priceKopecks: Long, val itemSku: String) {
    fun mulPrice(value: Long): Item {
        priceKopecks *= value
        return this
    }
}
