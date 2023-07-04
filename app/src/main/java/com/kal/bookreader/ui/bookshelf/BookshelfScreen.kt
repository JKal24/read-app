package com.kal.bookreader.ui.bookshelf

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kal.bookreader.R
import com.kal.bookreader.data.entity.Book

@Composable
@Preview
fun GetAndDisplayBooks() {
    //books = bookDao.getBooks().collectAsState(initial = listOf())
    val books = listOf(Book("book1","Ok","Yeah"),
        Book("book2","Ok","Hmm"),
        Book("book3","Ok","Hmm"),
        Book("book4","Ok","Hmm"))

    Scaffold(
        scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed)),
        content = { innerPadding ->
            Box (Modifier.fillMaxHeight().padding(innerPadding)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    books.forEach {
                        DisplayBook(book = it)
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

@Composable
fun DisplayBook(book : Book) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {

            }), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = book.book_src)
            Column {
                Text(text = book.book_name)
                Text(text = book.image_src)
            }
    }
}
