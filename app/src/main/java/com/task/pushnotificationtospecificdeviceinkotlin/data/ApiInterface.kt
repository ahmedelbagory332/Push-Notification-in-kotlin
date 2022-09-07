package com.task.pushnotificationtospecificdeviceinkotlin.data

import com.task.pushnotificationtospecificdeviceinkotlin.model.NotificationModel
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com/"
    }

    @Headers(
        "Authorization: key=put your key"
        ,
        "Content-Type:application/json"
    )

    @POST("fcm/send")
    suspend fun sendNotification( @Body notificationModel: NotificationModel): Response<ResponseBody>



}
