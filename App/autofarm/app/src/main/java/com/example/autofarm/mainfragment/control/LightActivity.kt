package com.example.autofarm.mainfragment.control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.lightcontrol.*
import java.lang.Exception

class LightActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var mqttClient: MyMqtt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lightcontrol);
        mqttClient = MyMqtt(this,"tcp://192.168.200.111:1883")
        try{
            mqttClient.connect(arrayOf<String>("iot/#"))
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        light_up.setOnClickListener(this)
        light_down.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var data:String = ""
        if(v?.id==R.id.light_up){
            data="light_up"
        }else{
            data="light_down"
        }
        publish(data)
    }

    fun publish(data:String){
        mqttClient.publish("mydata/greenhouse",data)
    }

}