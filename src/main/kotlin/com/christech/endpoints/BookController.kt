package com.christech.endpoints

import com.christech.books.Book
import com.christech.books.request.BookRequest
import com.christech.service.BookInsertionService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.validation.Valid

@Controller("/book")
class BookController(
    private val bookInsertionService: BookInsertionService
)
{
    @Post
    fun create(@Body @Valid bookRequest: BookRequest) =
            bookInsertionService.create(
                    book = bookRequest.toModel()
            )

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