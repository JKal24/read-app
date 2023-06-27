package com.kal.bookreader.domain.repository

import com.kal.bookreader.data.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getCurrentBooks() : Flow<List<Book>>
}