package com.christech.books.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable.Deserializable
data class SearchRequestBook(
        val title: String? = null,
        val author: String? = null
)
