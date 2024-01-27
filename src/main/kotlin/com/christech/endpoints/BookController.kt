package com.christech.endpoints

import com.christech.books.Book
import com.christech.books.request.BookRequest
import com.christech.service.BookService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import jakarta.validation.Valid

@Controller("/book")
class BookController(
    private val bookService: BookService
)
{
    @Post
    @Status(HttpStatus.CREATED)
    fun create(@Body @Valid bookRequest: BookRequest) =
            bookService.create(
                    book = bookRequest.toModel()
            )

    @Get
    fun getAll() =
            bookService.getAll()

    @Get("/{id}")
    fun getById(id: String) =
            bookService.getById()
    private fun BookRequest.toModel() : Book =
            Book(
                    title = this.title,
                    genre = this.genre,
                    author = this.author,
                    publishYear = this.publishYear,
                    dayCompleted = this.dayCompleted,
                    rating = this.rating
            )

}