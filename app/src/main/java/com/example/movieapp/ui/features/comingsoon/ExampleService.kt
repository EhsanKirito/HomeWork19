package com.example.movieapp.ui.features.comingsoon

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.provider.Settings
import com.example.movieapp.R
import com.example.movieapp.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ExampleService: Service() {
    private var wakeLock: PowerManager.WakeLock? = null
    private var isServiceStarted = false
    private lateinit var player: MediaPlayer
    private var job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true

        // starting the process
        player.start()
        scope.launch {
            repeat(15){
                println("number is: $it")
                delay(1000)
            }
            stopSelf()
        }

        if (intent != null) {
            val action = intent.action
            when (action) {
                Actions.START.name -> startService()
                Actions.STOP.name -> stopService()
                else -> println("This should never happen. No action in the received intent")
            }
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startService()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        }else{
            stopForeground(true)
        }
            player.stop()
            job.cancel()
    }

    private fun createNotification(): Notification {
        val notificationChannelId = "ENDLESS SERVICE CHANNEL"

        // depending on the Android API that we're dealing with we will have
        // to use a specific method to create the notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                notificationChannelId,
                "Endless Service notifications channel",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Endless Service channel"
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                it
            }
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        }

        val builder: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            this,
            notificationChannelId
        ) else Notification.Builder(this)

        return builder
            .setContentTitle("Endless Service")
            .setContentText("This is your favorite endless service working")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("Ticker text")
            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }

    private fun startService() {
        var notification = createNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification)
        }else{
            startService()
        }
        isServiceStarted = true
    }

    private fun stopService() {
        stopForeground(true)
        stopSelf()
        isServiceStarted = false
    }

}



enum class Actions{
    START, STOP
}