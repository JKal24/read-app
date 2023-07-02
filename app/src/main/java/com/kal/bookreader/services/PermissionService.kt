package com.kal.bookreader.services

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermission(permissionState: PermissionState) {
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)) {
        Column(Modifier.padding(vertical = 120.dp, horizontal = 16.dp)) {
            Spacer(Modifier.height(8.dp))
            Text("Permission required", style = MaterialTheme.typography.h6)
            Spacer(Modifier.height(4.dp))
            Text("This is required in order for the app to function")
        }
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                ContextCompat.startActivity(context, intent, null)
            }) {
            Text("Go to settings")
        }
    }
}