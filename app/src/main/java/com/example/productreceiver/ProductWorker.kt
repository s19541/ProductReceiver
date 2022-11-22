package com.example.productreceiver

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class ProductWorker (appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        try{
            val productId = inputData.getLong("productId", 0)
            val channelId = inputData.getString("channelId") ?: ""
            val name = inputData.getString("name")

            val addProductIntent = Intent().also {
                it.component = ComponentName("com.example.shoppingchartapp", "com.example.shoppingchartapp.EditActivity")
            }.putExtra("id", productId)

            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                1,
                addProductIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val notification = NotificationCompat.Builder(
                applicationContext,
                channelId
            ).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Added product")
                .setContentText(name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(applicationContext).notify(0, notification)
        }
        catch (e: java.lang.Exception){
            return Result.failure()
        }

        return Result.success()
    }
}