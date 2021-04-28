package com.example.autofarm.mainfragment.control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.degreecontrol.*
import kotlinx.android.synthetic.main.lightcontrol.*
import java.lang.Exception

class DegreeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mqttClient: MyMqtt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.degreecontrol);
        mqttClient = MyMqtt(this,"tcp://192.168.200.115:1883")
        try{
            mqttClient.connect(arrayOf<String>("iot/#"))
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        degree_up.setOnClickListener(this)
        degree_down.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var data:String = ""
        if(v?.id==R.id.degree_up){
            data="heat_on"
        }else{
            data="fan_on"
        }
        publish(data)
    }
    fun publish(data:String){
        mqttClient.publish("mydata/greenhouse",data)
    }
}