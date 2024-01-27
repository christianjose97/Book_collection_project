package com.christech.books.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Introspected
@Deserializable
data class BookRequest(
        val title: String,
        val genre: String,
        val author: String,
        val publishYear: Int,
        val dayCompleted: String,
        val rating: Int
)
