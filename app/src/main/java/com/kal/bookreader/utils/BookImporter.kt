package com.kal.bookreader.utils

import com.kal.bookreader.data.entity.Book
import com.kal.bookreader.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

fun importEpub(epub : EpubBook, repository: BookRepository, bookSrc: String) {

}

suspend fun importBookImage(
    targetFile: File,
    imageData: ByteArray,
) = withContext(Dispatchers.IO) {
    targetFile.parentFile?.also { parent ->
        parent.mkdirs()
        if (parent.exists()) {
            targetFile.writeBytes(imageData)
        }
    }
}