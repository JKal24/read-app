package com.kal.bookreader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.kal.bookreader.data.dao.BookDao
import com.kal.bookreader.data.entity.Book

class Bookshelf(private val bookDao : BookDao) {
    private lateinit var books : State<List<Book>>

    @Composable
    fun displayBooks() {
        //books = bookDao.getBooks().collectAsState(initial = listOf())
        val books = listOf(Book(0, "book1", "Yeah", "Okay", "111"))
        Column {
             books.forEach {
                Row {
                    displayBook(it)
                }
            }
        }
    }

    @Composable
    fun displayBook(book : Book) {
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

}