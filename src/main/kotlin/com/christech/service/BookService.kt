package com.christech.service

import com.christech.books.Book
import com.christech.repository.BookRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class BookService (
        private val bookRepository: BookRepository
) {

    fun create(book: Book): Book =
            bookRepository.save(book)

    fun getAll(): List<Book> =
            bookRepository.findAll()
                    .toList()

    fun getById(id: String): Book
    = bookRepository.findById(id)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "User with id $id was not found.") }

}