package com.example.productreceiver

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var receiver: AddProductReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = AddProductReceiver()
    }

    override fun onStart() {
        super.onStart()

        registerReceiver(
            receiver,
            IntentFilter("com.example.shoppingChartApp.action.AddProduct")
        )
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}