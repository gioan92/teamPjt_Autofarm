package com.example.autofarm.mainfragment

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autofarm.MainActivity
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.farmstate.*
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception
import kotlin.concurrent.thread

class FarmCondition : Fragment() {
    var dhtdata:String? = null
    lateinit var handler1: Handler


    lateinit var mqttClient1: MyMqtt
    var data:String =  ""
    var hum:Double = 0.0
    var degree:Double = 0.0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmstate, container, false);
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mqttClient1= MyMqtt(context!!, "tcp://192.168.0.187:1883")
        try{
            mqttClient1.setCallback(::onReceived) // callback일 때 메소드명만 입력
            mqttClient1.connect(arrayOf<String>("iot/#"))
        }catch(e: Exception){
            e.printStackTrace()
        }

        ceilimage.setImageResource(R.drawable.closeroof)

    }

    fun onReceived(topic:String, message: MqttMessage){
        val msg = String(message.payload)

        data = msg
        var datalist = data.split(",")
        var hum1 = datalist[0]
        var degree1 = datalist[1]
        var hum = hum1.toDouble()
        var degree = degree1.toDouble()




        degreeoutput.text = degree.toString() + "°C"
        humidityoutput.text = hum.toString() + "%"
    }
}