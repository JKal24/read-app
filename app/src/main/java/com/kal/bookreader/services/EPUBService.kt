package com.kal.bookreader.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.kal.bookreader.utils.ExtraUri
import com.kal.bookreader.utils.isServiceRunning

class EPUBService : Service() {

    companion object {
        fun start(ctx: Context, uri: Uri) {
            if (!isRunning(ctx))
                ContextCompat.startForegroundService(ctx, IntentData(ctx, uri))
        }

        private fun isRunning(context: Context): Boolean =
            context.isServiceRunning(EPUBService::class.java)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private class IntentData : Intent {
        var uri by ExtraUri()

        constructor(intent: Intent) : super(intent)
        constructor(ctx: Context, uri: Uri) : super(ctx, EPUBService::class.java) {
            this.uri = uri
        }
    }

}