package com.christech.controller

import com.christech.books.request.BookRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.common.mapper.TypeRef
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class BookControllerIT {
    @Test
    fun `should return 200 OK on GET users` (spec: RequestSpecification) {
        spec
            .`when`()
            .get("/book")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json")
    }

    @Test
    fun `should return 404 NOT FOUND on GET user by id` (spec: RequestSpecification) {
        spec
            .`when`()
            .get("/book/111111111111111111111111")
            .then()
            .statusCode(404)
            .header("Content-Type", "application/json")
    }

    @Test
    fun `should create book entry` (spec: RequestSpecification) {
        val request = BookRequest(
            title = "IT test title",
            genre = "IT test genre",
            author = "this.author",
            publishYear = 2023,
            dayCompleted = "02-01-2024",
            rating = 4
        )
        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/book")
            .then()
            .statusCode(201)

        val list = spec
            .`when`()
            .get("/book")
            .then()
            .statusCode(200)
        //verifies that size is equal to 1
            .extract()
            .`as`(object : TypeRef<List<com.christech.books.Book>>() {})

        // Find the created book in the list by title
        val createdBook = list.find { it.title == request.title }
            ?: throw AssertionError("Created book with title '${request.title}' not found")

        assertEquals(request.title, createdBook.title)
        assertEquals(request.genre, createdBook.genre)
        assertEquals(request.author, createdBook.author)
        assertEquals(request.publishYear, createdBook.publishYear)
        assertEquals(request.dayCompleted, createdBook.dayCompleted)
        assertEquals(request.rating, createdBook.rating)

        //Clean up and delete IT test booka
        spec
            .`when`()
            .delete("/book/${createdBook.id}")
            .then()
            .statusCode(204)


    }
}