package com.kal.bookreader.ui.bookshelf

import androidx.lifecycle.ViewModel
import com.kal.bookreader.domain.repository.BookRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookshelfViewModel @Inject constructor(bookRepository: Lazy<BookRepository>) : ViewModel() {
    init {
        bookRepository.get()
    }
}