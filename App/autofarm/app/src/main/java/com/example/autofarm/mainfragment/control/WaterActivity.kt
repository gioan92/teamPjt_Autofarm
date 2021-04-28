package com.example.autofarm.mainfragment.control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.watercontrol.*
import java.lang.Exception
import kotlin.concurrent.thread

class WaterActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mqttClient: MyMqtt;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.watercontrol);

        mqttClient = MyMqtt(this,"tcp://192.168.200.115:1883");

        try {
            mqttClient.connect(arrayOf<String>("iot/#"));
        } catch(e: Exception) {
            e.printStackTrace();
        }

        water.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        var data:String = "";
        if (v?.id == R.id.water) {
            data = "water_on";

        }
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        publish(data);
    }

    fun publish(data: String) {
        Log.d("mydata", data)
        mqttClient.publish("mydata/greenhouse", data)
    }

}