package com.kal.bookreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kal.bookreader.data.AppDatabase
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.ui.theme.BookReaderTheme

class MainActivity : ComponentActivity() {
    private val appDatabase = AppDatabase.getAppDataBase(this)
    private lateinit var bookshelf : Bookshelf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookshelf = Bookshelf(appDatabase.BookDao())
        setContent {
            BookReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    displayBookshelf()
                }
            }
        }
    }

    @Composable
    @Preview
    fun displayBookshelf() {
        bookshelf.displayBooks()
    }
}