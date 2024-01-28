package com.christech.endpoints

import com.christech.books.Book
import com.christech.books.request.BookRequest
import com.christech.service.BookService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
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

    @Put("/{id}")
    fun update (
                id: String, @Body @Valid
            request: BookRequest,
            @Header("X-test") header: String
    ): Book {
        println("Header: $header")

        return bookService.update(id, request.toModel())
    }

    @Get("/{id}")
    fun getById(id: String) =
            bookService.getById(id)
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