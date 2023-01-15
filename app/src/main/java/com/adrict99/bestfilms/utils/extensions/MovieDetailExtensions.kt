package com.adrict99.bestfilms.utils.extensions

import com.adrict99.bestfilms.domain.model.detail.Country
import com.adrict99.bestfilms.domain.model.detail.Genre
import com.adrict99.bestfilms.domain.model.detail.Language
import com.adrict99.bestfilms.utils.extra.CountryFlags
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun Double.round(decimals: Int): Double =
    BigDecimal(this).setScale(decimals, RoundingMode.HALF_EVEN).toDouble()

fun Int.asMinutes(): String = if (this == 1) "$this min" else "$this mins"

fun List<Genre>.genreJoinToString(): String = this.joinToString { it.name }

fun List<Language>.languageJoinToString(): String = this.joinToString { it.name }

fun List<Country>.toUnicodeFlag(): String =
    this.joinToString { countryCodeToEmojiFlag(it.iso31661) }

fun countryCodeToEmojiFlag(countryCode: String): String {
    return countryCode
        .uppercase(Locale.US)
        .map { char ->
            Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
        }
        .map { codePoint ->
            Character.toChars(codePoint)
        }
        .joinToString(separator = "") { charArray ->
            String(charArray)
        }
}