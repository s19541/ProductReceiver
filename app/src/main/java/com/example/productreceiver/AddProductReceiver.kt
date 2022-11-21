package com.example.productreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AddProductReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == "com.example.shoppingChartApp.action.AddProduct"){
            val channelId = createChannel(context)
            val name = intent.extras?.getString("name")
            val id = intent.extras?.getLong("id") as Long

            val addProductIntent = Intent().also {
                it.component = ComponentName("com.example.shoppingchartapp", "com.example.shoppingchartapp.EditActivity")
            }.putExtra("id", id)

            val pendingIntent = PendingIntent.getActivity(
                context,
                1,
                addProductIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val notification = NotificationCompat.Builder(
                context,
                channelId
            ).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Added product")
                .setContentText(name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context).notify(0, notification)
        }
        else{
            Toast.makeText(context, "Unknown action", Toast.LENGTH_SHORT).show()
        }

    }

    private fun createChannel(context: Context): String {
        val id = "ProductAddChannel"
        val channel = NotificationChannel(
            id,
            "Product Add Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        NotificationManagerCompat.from(context).createNotificationChannel(channel)
        return id
    }
}