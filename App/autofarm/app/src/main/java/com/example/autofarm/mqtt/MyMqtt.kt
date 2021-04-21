package com.example.autofarm.mqtt

import android.content.Context
import android.util.Log
import com.example.autofarm.mainfragment.FarmCondition
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MyMqtt(var context: Context, val uri:String) {
    var mqttClient:MqttAndroidClient = MqttAndroidClient(context,uri, MqttClient.generateClientId())

    // 등록한 토픽에 맞는 메세지가 도착하면 사용자정의 메소드를 실행할 수 있도록 callback 구현 --- 3번
    fun setCallback(callback : (topic:String, message:MqttMessage)->Unit){
        mqttClient.setCallback(object : MqttCallback{
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                // 서버가 메세지를 보내주면 callback 메소드로 등록한 onReceived 메소드를 호출하며 서버로부터 전달받은
                // 토픽정보와 메시지를 전달해준다.
                callback(topic!!, message!!)
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d("mymqtt", "connectionLost")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("mymqtt", "deliveryComplete")
            }
        })
    }

    fun connect(topics : Array<String>?=null){
        val mqttconnect_options = MqttConnectOptions()
        //connect 호출 - broker에 연결
        mqttClient.connect(mqttconnect_options,null, object:IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("mymqtt","접속성공......")

                topics?.map{subscribeTopic(it)}
            }
            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("mymqtt","접속실패......")
            }
        })
    }

    // 토픽 subscribe 등록 - 토픽을 서버에 등록하기 위한 메소드  --- 1번
    private fun subscribeTopic(topic: String, qos:Int = 0)
    {
        mqttClient.subscribe(topic, qos, null, object : IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("mymqtt", "subscribe 성공")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("mymqtt", "subscribe 실패")
            }
        })
    }

    //broker에 publish 할 때 사용할 메소드
    fun publish(topic:String, payload: String, qos:Int=0){
        if(mqttClient.isConnected() === false){
            mqttClient.connect()
        }
        val message = MqttMessage()
        message.payload = payload.toByteArray() //String을 byte 배열로 변환 - 네트워크로 전송
        message.qos = qos
        mqttClient.publish(topic,message,null,object:IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("mymqtt","publish성공");
            }
            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("mymqtt","publish실패")
            }
        })
    }



//        mqttClient.publish(topic,message,null,object:IMqttActionListener{
//            override fun onSuccess(asyncActionToken: IMqttToken?) {
//                Log.d("mymqtt","publish성공");
//            }
//
//            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
//                Log.d("mymqtt","publish실패")
//            }
//        })
    }

