package com.christech.service

import com.christech.repository.BookRepository
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BookServiceTest {
    private val bookRepository = mockk<BookRepository>()

    private val bookService = BookService(bookRepository)

    @Test
    fun `should return empty user list`() {
        every {
            bookRepository.findAll()
        } returns emptyList()

        val result = bookService.getAll()

        assertTrue(result.isEmpty())

        verify(exactly = 1) { bookRepository.findAll() }
    }
}