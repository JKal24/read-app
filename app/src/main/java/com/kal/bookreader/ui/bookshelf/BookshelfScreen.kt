package com.kal.bookreader.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.ui.bookshelf.BookshelfViewModel


@Composable
fun BookshelfScreen(bookshelfViewModel: BookshelfViewModel = hiltViewModel()) {
    //books = bookDao.getBooks().collectAsState(initial = listOf())
    val books = listOf(Book(0, "book1", "Yeah", "Okay", "111"))
    Column {
         books.forEach {
            Row {
                DisplayBook(book = it)
            }
        }
    }
}

@Composable
fun DisplayBook(book : Book) {
    Column {
        Text(text = book.imageSrc) // Replaced with an image later once internal storage is set up
    }
    Column {
        Row {
            Text(text = book.book_name + " by " + book.author)
        }
        Row {
            Text(text = book.book_description)
        }
    }
}