package ru.wa5teed.reactivity.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("Item")
data class Item(@Id val id: Long, var priceKopecks: BigDecimal, val itemSku: String) {
    fun adjustPrice(coefficient: BigDecimal): Item {
        priceKopecks.multiply(coefficient)
        return this
    }
}
