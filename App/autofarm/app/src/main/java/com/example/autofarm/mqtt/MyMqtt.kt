package com.example.autofarm.mqtt

import android.content.Context
import android.service.voice.AlwaysOnHotwordDetector
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MyMqtt(val context: Context, val uri:String) {
    var mqttClient:MqttAndroidClient = MqttAndroidClient(context,uri, MqttClient.generateClientId())

    fun connect(topics : Array<String>?=null){
        val mqttconnect_options = MqttConnectOptions()
        //connect 호출 - broker에 연결
        mqttClient.connect(mqttconnect_options,null, object:IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("mymqtt","접속성공......")
            }
            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("mymqtt","접속실패......")
            }
        })
    }
    //broker에 publish 할 때 사용할 메소드
    fun publish(topic:String, payload: String, qos:Int=0){
        if(mqttClient.isConnected() == false){
            mqttClient.connect()
        }
        val message = MqttMessage()
        message.payload = payload.toByteArray() //String을 byte 배열로 변환 - 네트워크로 전송
        message.qos = qos
        mqttClient.publish(topic,message,null,object:IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("mymqtt","publish성공")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("mymqtt","publish실패")
            }
        })
    }
}
