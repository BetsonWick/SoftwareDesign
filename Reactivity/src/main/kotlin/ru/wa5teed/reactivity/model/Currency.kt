package ru.wa5teed.reactivity.model

import java.math.BigDecimal

enum class Currency(val value: BigDecimal) {
    RUB(BigDecimal(1)),
    USD(BigDecimal(0.014)),
    EUR(BigDecimal(0.0125)),
}
