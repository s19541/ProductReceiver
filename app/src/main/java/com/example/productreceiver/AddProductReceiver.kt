package com.example.productreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

class AddProductReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == "com.example.shoppingChartApp.action.AddProduct"){
            val data = intent.getStringExtra("Name")
            Log.i("BR", "Data received:  $data")

            /*val channelId = createChannel(context)

            val addProductIntent = Intent().also {
                it.component = ComponentName(context, "com.example.shoppingChartApp")
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                1,
                addProductIntent,
                PendingIntent.FLAG_MUTABLE
            )

            val notification = NotificationCompat.Builder(
                context,
                channelId
            ).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Added product")
                .setContentText(intent.getStringExtra("Name"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context).notify(0, notification)*/
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