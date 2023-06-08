package com.lastminute.ui.model

import java.time.LocalDate

data class PriceTerm(
    var since: LocalDate,
    val originPrice: Int,
    var percent: Int,
    var price: Int
)
