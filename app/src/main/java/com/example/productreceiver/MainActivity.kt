package com.example.productreceiver

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var receiver: AddProductReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = AddProductReceiver()

        registerReceiver(
            receiver,
            IntentFilter("com.example.shoppingChartApp.action.AddProduct")
        )

    }

    /*override fun onStart() {
        super.onStart()


    }*/

  /*  override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }*/


}