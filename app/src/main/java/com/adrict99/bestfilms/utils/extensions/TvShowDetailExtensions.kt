package com.adrict99.bestfilms.utils.extensions

import com.adrict99.bestfilms.domain.model.detail.Language

fun List<String>.toUnicodeFlag(): String =
    this.joinToString { countryCodeToEmojiFlag(it) }

fun List<Int>.calculateAverage(): Double = this.average()

fun Double.asMinutes(): String = if (this > 0) "${this.toInt()} min." else "-"

fun List<Language>.toOriginalLanguage(): String = if (this.isEmpty()) "Unknown" else this.first().name