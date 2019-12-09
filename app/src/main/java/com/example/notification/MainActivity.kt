package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder
    val channelId="com.example.notification"
    val description="My Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val show=findViewById<Button>(R.id.btn_show)

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        show.setOnClickListener {

            val intent=Intent(applicationContext,MainActivity::class.java)
            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)

                notificationChannel.enableLights(true)
                notificationChannel.lightColor=Color.RED
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)


                builder=Notification.Builder(this,channelId)
                    .setContentTitle("Android")
                    .setContentText("New Message")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)

            }
            else
            {
                builder=Notification.Builder(this)
                    .setContentTitle("Android")
                    .setContentText("New Message")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notificationManager.notify(0,builder.build())
            }
        }

    }
}
