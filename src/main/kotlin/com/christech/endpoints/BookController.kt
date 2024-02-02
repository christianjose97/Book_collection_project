package com.christech.endpoints

import com.christech.books.Book
import com.christech.books.request.BookRequest
import com.christech.books.request.SearchRequestBook
import com.christech.service.BookService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid

@Controller("/book")
@ExecuteOn(TaskExecutors.IO)
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

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(@Body @Valid id: String) {
        bookService.delete(id)
    }

    @Post("/search")
    fun search(@Body @Valid searchRequest: SearchRequestBook) : List<Book> =
        bookService.search(searchRequest)
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