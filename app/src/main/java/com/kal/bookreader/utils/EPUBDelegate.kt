package com.kal.bookreader.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlin.reflect.KProperty

fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
    val className = serviceClass.name
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return manager.getRunningServices(Integer.MAX_VALUE)
        .any { className == it.service.className }
}

class ExtraUri {
    operator fun getValue(thisRef: Intent, property: KProperty<*>) =
        thisRef.extras!!.get(property.name) as Uri

    operator fun setValue(thisRef: Intent, property: KProperty<*>, value: Uri) =
        thisRef.putExtra(property.name, value)
}