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
        "Authorization: key=AAAAbj9kVrI:APA91bGOe6VSJh_M9b0bmxfod02R_iL_Ahx-2SOep4WqpYDYjcJmJktvyxClHbiT3Mm2WVTd7GGuDWDL1swcFWKnZl-ThgTzi06WkW_VahuEUjv_nH4ZuYJ_I_HsvJuvMbVgh_jkpt8b"
        ,
        "Content-Type:application/json"
    )

    @POST("fcm/send")
    suspend fun sendNotification( @Body notificationModel: NotificationModel): Response<ResponseBody>



}