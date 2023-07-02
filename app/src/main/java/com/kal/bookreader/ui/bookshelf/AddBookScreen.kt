package com.kal.bookreader.ui.bookshelf

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kal.bookreader.services.BookService.Companion.start

@Composable
fun findEPUB(): () -> Unit {
    val context = LocalContext.current
    val fileExplorer = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null)
                start(context = context, uri = uri)
        }
    )
    return { fileExplorer.launch("application/epub+zip") }
}
