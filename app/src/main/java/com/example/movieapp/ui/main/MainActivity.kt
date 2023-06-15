package com.example.movieapp.ui.main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var builder = NotificationCompat.Builder(this, "ID")
            .setSmallIcon(R.drawable.baseline_favorite_24)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel()

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(2, builder.build())
        }


    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_name"
            val descriptionText = "channel_description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}