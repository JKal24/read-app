package com.kal.bookreader.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat
import com.kal.bookreader.domain.repository.BookRepository
import com.kal.bookreader.utils.ExtraUri
import com.kal.bookreader.utils.asSequence
import com.kal.bookreader.utils.epubParser
import com.kal.bookreader.utils.isServiceRunning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookService : Service() {

    @Inject
    lateinit var bookRepository: BookRepository

    private var serviceJob: Job? = null

    companion object {
        fun start(context: Context, uri: Uri) {
            if (!isRunning(context))
                ContextCompat.startForegroundService(context, IntentUri(context, uri))
        }

        private fun isRunning(context: Context): Boolean =
            context.isServiceRunning(BookService::class.java)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        serviceJob?.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val intentUri = IntentUri(intent)

            serviceJob = CoroutineScope(Dispatchers.IO).launch {
                val inputStream = contentResolver.openInputStream(intentUri.uri) ?: return@launch

                val fileName = contentResolver.query(
                    intentUri.uri,
                    arrayOf(OpenableColumns.DISPLAY_NAME),
                    null,
                    null,
                    null,
                    null
                ).asSequence().map { it.getString(0) }.last()

                val epub = inputStream.use { epubParser(inputStream = it) }

                stopSelf(startId)
            }
        }
        return START_NOT_STICKY
    }

    private class IntentUri : Intent {
        var uri by ExtraUri()

        constructor(intent: Intent) : super(intent)
        constructor(ctx: Context, uri: Uri) : super(ctx, BookService::class.java) {
            this.uri = uri
        }
    }
}