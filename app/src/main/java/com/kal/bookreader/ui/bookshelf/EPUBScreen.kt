package com.kal.bookreader.ui.bookshelf

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.kal.bookreader.services.EPUBService.Companion.start

@Composable
fun findEPUB(): () -> Unit {
    val context = LocalContext.current
    val fileExplorer = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null)
                start(ctx = context, uri = uri)
        }
    )
    return { fileExplorer.launch("application/epub+zip") }
}
