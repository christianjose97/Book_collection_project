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
            genre = "IT test title",
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
            .body("size()", `is` (1))
        //verifies that size is equal to 1
            .extract()
            .`as`(object : TypeRef<List<com.christech.books.Book>>() {})

        assertEquals(1, list.size)

        val createdBook = list.first()
        assertEquals(request.title, createdBook.title)

    }
}