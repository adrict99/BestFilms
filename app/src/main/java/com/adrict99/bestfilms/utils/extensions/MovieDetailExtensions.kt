package com.adrict99.bestfilms.utils.extensions

import com.adrict99.bestfilms.domain.model.detail.Genre
import com.adrict99.bestfilms.utils.extra.CountryFlags
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.round(decimals: Int): Double =
    BigDecimal(this).setScale(decimals, RoundingMode.HALF_EVEN).toDouble()

fun Int.asMinutes(): String = if (this == 1) "$this min" else "$this mins"

fun List<Genre>.genresListJoinToString(): String = this.joinToString { it.name }

fun String.toNationalFlagUnicode() : String = CountryFlags.getCountryFlagByCountryCode(this)