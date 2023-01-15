package com.adrict99.bestfilms.utils.extensions

import com.adrict99.bestfilms.domain.model.detail.Language

fun List<String>.toUnicodeFlag(): String =
    this.joinToString { countryCodeToEmojiFlag(it) }

fun List<Int>.calculateAverage(): Double = this.average()

fun Double.asMinutes(): String = "${this.toInt()} min."

fun List<Language>.toOriginalLanguage(): String = this.first().name