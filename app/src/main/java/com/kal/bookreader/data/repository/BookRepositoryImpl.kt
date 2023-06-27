package com.kal.bookreader.data.repository

import com.kal.bookreader.data.dao.BookDao
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDao: BookDao): BookRepository {

    override suspend fun getCurrentBooks(): Flow<List<Book>> = bookDao.getBooks()

}