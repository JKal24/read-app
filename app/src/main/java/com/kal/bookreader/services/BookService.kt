package com.kal.bookreader.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.provider.OpenableColumns
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.kal.bookreader.domain.repository.BookRepository
import com.kal.bookreader.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BookService : Service() {

    @Inject
    lateinit var bookRepository: BookRepository

    @Inject
    lateinit var fileResolver: FileResolver

    private var serviceJob: Job? = null

    companion object {
        fun start(context: Context, uri: Uri) {
            if (!isRunning(context))
                ContextCompat.startForegroundService(context, IntentUri(context, uri))
        }

        private fun isRunning(context: Context): Boolean =
            context.isServiceRunning(BookService::class.java)
    }

    private val id = "add epub"
    override fun onCreate() {
        super.onCreate()

        val manager = NotificationManagerCompat.from(this)
        val channel = NotificationChannel(id.hashCode().toString(), id, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(this, id.hashCode().toString())
            .setContentTitle(id)
            .setContentText(id)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        startForeground(id.hashCode(), builder.build())
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

                val fileCursor = contentResolver.query(
                    intentUri.uri,
                    arrayOf(OpenableColumns.DISPLAY_NAME),
                    null,
                    null,
                    null,
                    null
                )
                val fileName = fileCursor.asSequence().map { it.getString(0) }.last()
                fileCursor?.close()

                val epub = inputStream.use { epubParser(inputStream = it) }
                val bookCursor = contentResolver.query(
                    intentUri.uri,
                    arrayOf(OpenableColumns.DISPLAY_NAME),
                    null,
                    null,
                    null,
                    null
                )
                val bookSrc = bookCursor.asSequence().map { it.getString(0) }.last()
                bookCursor?.close()
                importEpub(fileName, epub, bookRepository, bookSrc, fileResolver)

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