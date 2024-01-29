package com.christech.service

import com.christech.books.Book
import com.christech.books.request.SearchRequestBook
import com.christech.repository.BookRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import org.bson.types.ObjectId

@Singleton
class BookService (
        private val bookRepository: BookRepository
) {

    //TODO: Add try catch blocks for un expected errors

    fun create(book: Book): Book =
            bookRepository.save(book)

    fun getAll(): List<Book> =
            bookRepository.findAll()
                    .toList()

    fun getById(id: String): Book {
        if (!ObjectId.isValid(id)) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "Invalid ID format: $id")
        }

        return bookRepository.findById(id)
                .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Book with ID $id was not found.") }
    }

    fun update(id: String, book: Book): Book {
        val found = getById(id)

        val updated = book.copy(id = found.id)

        return bookRepository.update(updated)
    }

    fun delete(id: String): Any {
        val found = getById(id)

        return bookRepository.delete(found)
    }

    fun search(searchRequest: SearchRequestBook): List<Book> =
        when {
            searchRequest.title != null -> searchByTitle(searchRequest.title)
            searchRequest.author != null -> searchByAuthor(searchRequest.author)
            else -> emptyList()

        }

    private fun searchByAuthor(author: String) : List<Book> =
        bookRepository.findByAuthor(author)

    private fun searchByTitle(title: String) : List<Book> =
        emptyList()

}