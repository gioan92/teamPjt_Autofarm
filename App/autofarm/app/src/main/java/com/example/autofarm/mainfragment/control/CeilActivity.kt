package com.example.autofarm.mainfragment.control

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.autofarm.R
import com.example.autofarm.mainfragment.FarmCondition
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.ceilcontrol.*
import kotlinx.android.synthetic.main.farmstate.*
import java.lang.Exception

class CeilActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mqttClient: MyMqtt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ceilcontrol);
        mqttClient = MyMqtt(this,"tcp://192.168.200.111:1883")
        try{
            mqttClient.connect(arrayOf<String>("iot/#"))
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        ceil_open.setOnClickListener(this)
        ceil_close.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var data:String = ""
        if(v?.id==R.id.ceil_open){
            data="ceil_open"
        }else{
            data="ceil_close"
        }
        publish(data)
    }
    fun publish(data:String){
        mqttClient.publish("mydata/greenhouse",data)
    }
}