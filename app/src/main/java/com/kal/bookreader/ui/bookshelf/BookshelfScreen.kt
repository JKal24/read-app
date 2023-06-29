package com.kal.bookreader.ui.bookshelf

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.hilt.navigation.compose.hiltViewModel
import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.R


@Composable
@Preview
fun BookshelfScreen(bookshelfViewModel: BookshelfViewModel = hiltViewModel()) {
    //books = bookDao.getBooks().collectAsState(initial = listOf())
    val books = listOf(Book(0, "book1", "Yeah", "Okay", "111"),
        Book(1, "book2", "Hmm", "No", "111"),
        Book(1, "book3", "Hmm", "No", "111"),
        Book(1, "book4", "Hmm", "No", "111"))
    val context = LocalContext.current
    Box(Modifier.fillMaxHeight()) {
        Column(Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            books.forEach {
                DisplayBook(book = it)
            }
        }
        Column(Modifier.fillMaxHeight().fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
            Row(Modifier.fillMaxWidth().padding(0.dp, 0.dp, 10.dp, 0.dp), horizontalArrangement = Arrangement.End) {
                Image(painterResource(id = R.drawable.add), contentDescription = null,
                    modifier = Modifier.clickable(onClick = {
                        Toast.makeText(context, "Image", Toast.LENGTH_SHORT).show()
                    } ).size(50.dp, 100.dp)
                )
            }
        }
    }

}

@Composable
fun DisplayBook(book : Book) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { result ->
        val item = result?.let { context.contentResolver.openInputStream(it) }
        val bytes = item?.readBytes()
        println(bytes)
    }

    Row(Modifier.fillMaxWidth().clickable(onClick = {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "text/plain"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(context as Activity, intent, 1, null)
    }), horizontalArrangement = Arrangement.SpaceEvenly) {
        Box() {
            Text(text = book.imageSrc) // Replaced with an image later once internal storage is set up
        }
        Column {
            Box() {
                Text(text = book.book_name + " by " + book.author)
            }
            Box() {
                Text(text = book.book_description)
            }
        }
    }

}