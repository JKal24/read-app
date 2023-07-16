package com.kal.bookreader.ui.bookshelf

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.kal.bookreader.R
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.ui.views.BookCardView
import java.io.File
import java.nio.file.Paths

@Composable
fun GetAndDisplayBooks(bookshelfViewModel: BookshelfViewModel = viewModel()) {
    val books = bookshelfViewModel.getBooks().collectAsState(initial = listOf())
    val context by rememberUpdatedState(LocalContext.current)

    Scaffold(
        scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed)),
        content = { innerPadding ->
            Box (
                Modifier
                    .fillMaxHeight()
                    .padding(innerPadding)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    books.value.forEach {
                        BookCardView(
                            bookTitle = it.book_name,
                            bookClick = { handleReadingBook(context) }
                        )
                    }
                }

                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 10.dp, 0.dp), horizontalArrangement = Arrangement.End) {
                        Image(painterResource(id = R.drawable.add), contentDescription = null,
                            modifier = Modifier
                                .clickable(onClick = findEPUB())
                                .size(50.dp, 100.dp)
                        )
                    }
                }
            }
        }
    )
}

fun handleReadingBook(context: Context) {

}