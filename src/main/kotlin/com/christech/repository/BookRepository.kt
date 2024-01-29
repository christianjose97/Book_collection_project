package com.christech.repository

import com.christech.books.Book
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface BookRepository : CrudRepository<Book, String> {
    fun findByAuthor(author: String) : List<Book>
}