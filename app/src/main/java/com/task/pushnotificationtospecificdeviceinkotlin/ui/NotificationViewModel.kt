package com.task.pushnotificationtospecificdeviceinkotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.task.pushnotificationtospecificdeviceinkotlin.data.ApiInterface
import com.task.pushnotificationtospecificdeviceinkotlin.model.NotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val api: ApiInterface) : ViewModel() {



    private val _connectionError = MutableStateFlow("")
    private val _response = MutableStateFlow("")


    val connectionError: LiveData<String>
        get() = _connectionError.asLiveData()

    val response: LiveData<String>
        get() = _response.asLiveData()


    fun sendNotification(notificationModel: NotificationModel) {


        viewModelScope.launch(Dispatchers.IO) {
            _connectionError.emit("sending")
            try {
                val response = api.sendNotification(notificationModel)
                if (response.isSuccessful) {
                    //_response.emit(response.toString())
                    Log.d("TAG", "Notification in Kotlin: ${response.body()} ")

                    _connectionError.emit("sent")
                } else {
                    _connectionError.emit("error while sending")
                    Log.d("TAG", "Notification in Kotlin1: ${response.errorBody()} ")

                }
            }catch (e:Exception){
                _connectionError.emit("error while sending")
                Log.d("TAG", "Notification in Kotlin2: ${e.message} ")

            }
        }

    }



}