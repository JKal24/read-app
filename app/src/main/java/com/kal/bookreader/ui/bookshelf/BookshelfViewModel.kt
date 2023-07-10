package com.kal.bookreader.ui.bookshelf

import androidx.lifecycle.ViewModel
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.domain.repository.BookRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BookshelfViewModel @Inject constructor(private val bookRepository: Lazy<BookRepository>) : ViewModel() {

    fun getBooks(): Flow<List<Book>> = bookRepository.get().getCurrentBooks()
}