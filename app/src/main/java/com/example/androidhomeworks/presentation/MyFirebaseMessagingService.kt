package com.example.androidhomeworks.presentation

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.androidhomeworks.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val FIREBASE_TAG = "FIREBASE_TAG"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(FIREBASE_TAG, "New Firebase token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            val title = it.title
            val body = it.body
            sendNotification(title, body)
        }
    }

    private fun sendNotification(title: String?, body: String?) {
        val channelId = getString(R.string.default_channel)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        notificationManager.notify(0, notification)
    }
}
