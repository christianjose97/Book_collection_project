package com.christech.service

import com.christech.books.Book
import com.christech.repository.BookRepository
import jakarta.inject.Singleton

@Singleton
class BookInsertionService (
        private val bookRepository: BookRepository
) {

    fun create(book: Book): Book =
            bookRepository.save(book)

}