package com.kal.bookreader.domain.repository

import com.kal.bookreader.data.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getCurrentBooks() : Flow<List<Book>>
    suspend fun addBooks(vararg books : Book)

    suspend fun removeBook(bookUri: String)
}