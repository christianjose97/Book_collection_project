package com.christech.books

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class Book(
        @field: Id
        @field: GeneratedValue
        val id: String? = null,
        val title: String,
        val genre: String,
        val author: String,
        val publishYear: Int,
        val dayCompleted: String,
        val rating: Int
)
