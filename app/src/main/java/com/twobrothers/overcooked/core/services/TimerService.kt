package com.twobrothers.overcooked.core.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.twobrothers.overcooked.R

class TimerService : Service() {

    companion object {
        private const val CHANNEL_ID = "Overcooked.ChannelId"
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_NAME = "Overcooked.ChannelName"
        private const val CHANNEL_DESCRIPTION = "Overcooked.ChannelDescription"
        private const val EXTRA_NOTIFICATION_TITLE = "Notification.Title"
        private const val EXTRA_NOTIFICATION_TEXT = "Notification.Text"

        @JvmStatic
        fun newIntent(launchContext: Context, title: String, text: String): Intent {
            val intent = Intent(launchContext, TimerService::class.java)
            intent.putExtra(EXTRA_NOTIFICATION_TITLE, title)
            intent.putExtra(EXTRA_NOTIFICATION_TEXT, text)
            return intent
        }
    }

    private lateinit var notificationManager: NotificationManager

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val title = intent?.getStringExtra(EXTRA_NOTIFICATION_TITLE) ?: ""
        val text = intent?.getStringExtra(EXTRA_NOTIFICATION_TEXT) ?: ""
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        showNotification(title, text)
        return START_STICKY
    }

    private fun showNotification(title: String, text: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder
            .setOngoing(true)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_access_time)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        startForeground(NOTIFICATION_ID, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val importance = NotificationManager.IMPORTANCE_LOW
        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        notificationChannel.description = CHANNEL_DESCRIPTION
        notificationChannel.setShowBadge(false)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }
}