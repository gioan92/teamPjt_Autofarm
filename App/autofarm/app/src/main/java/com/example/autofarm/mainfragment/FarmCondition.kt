package com.example.autofarm.mainfragment

import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autofarm.R
import com.example.autofarm.mainfragment.control.*
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.farmstate.*
import kotlinx.android.synthetic.main.farmstate.ceilingcontrolbutton
import kotlinx.android.synthetic.main.farmstate.degreecontrolbutton
import kotlinx.android.synthetic.main.farmstate.lightcontrolbutton
import kotlinx.android.synthetic.main.farmstate.waterbutton
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception
import kotlin.concurrent.thread

class FarmCondition : Fragment() {
    lateinit var mqttClient1: MyMqtt
    lateinit var handler1: Handler
    var data:String =  ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmstate, container, false);

        handler1 = object : Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                var datalist = data.split(",")
                var hum1 = datalist[0]
                var degree1 = datalist[1]
                var hum = hum1.toDouble()
                var degree = degree1.toDouble()
                degreeoutput.text = degree.toString() + "°C"
                humidityoutput.text = hum.toString() + "%"
            }
        }

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        degreecontrolbutton.setOnClickListener {
            val degreeIntent = Intent(activity, DegreeActivity::class.java).apply {
            };
            startActivity(degreeIntent);
        }
        waterbutton.setOnClickListener {
            val waterIntent = Intent(activity, WaterActivity::class.java).apply {
            };
            startActivity(waterIntent);
        }
        lightcontrolbutton.setOnClickListener {
            val lightIntent = Intent(activity, LightActivity::class.java).apply {
            };
            startActivity(lightIntent);
        }
        ceilingcontrolbutton.setOnClickListener {
            val ceilIntent = Intent(activity, CeilActivity::class.java).apply {
            };
            startActivity(ceilIntent);
        }
        thread{
            mqttClient1 = MyMqtt(context!!, "tcp://192.168.0.187:1883")
            try {
                mqttClient1.setCallback(::onReceived) // callback일 때 메소드명만 입력
                mqttClient1.connect(arrayOf<String>("iot/#"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        ceilimage.setImageResource(R.drawable.closeroof)
    }

    fun onReceived(topic:String, message: MqttMessage) {
        val msg = String(message.payload)
        data = msg
        handler1.sendMessage(handler1.obtainMessage())
    }


}