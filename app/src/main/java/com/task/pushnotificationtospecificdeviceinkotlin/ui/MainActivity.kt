package com.task.pushnotificationtospecificdeviceinkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.messaging.FirebaseMessaging
import com.task.pushnotificationtospecificdeviceinkotlin.R
import com.task.pushnotificationtospecificdeviceinkotlin.model.Data
import com.task.pushnotificationtospecificdeviceinkotlin.model.NotificationModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationViewModel.connectionError.observe(this){
            when(it){
                "sending"-> {
                    Toast.makeText(this, "sending notification", Toast.LENGTH_SHORT).show()
                }
                "sent"-> {
                    Toast.makeText(this, "notification sent", Toast.LENGTH_SHORT).show()
                }
                "error while sending"-> {
                    Toast.makeText(this, "error while sending", Toast.LENGTH_SHORT).show()
                }
            }
        }

        notificationViewModel.response.observe(this){
             if (it.isNotEmpty())
            Log.d(TAG, "Notification in Kotlin: $it ")
        }

    }

    fun push(view: View) {


        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
            Log.d(TAG, "push: $token")

            notificationViewModel
                .sendNotification(
                    NotificationModel(
                            token,
                            Data("FCM Notification","this notification from android")
                        )
                    )
        }


    }
}