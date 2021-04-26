package com.example.autofarm.mainfragment.control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.watercontrol.*
import kotlin.concurrent.thread

class WaterActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mqttClient: MyMqtt;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.watercontrol);

        thread {
            mqttClient = MyMqtt(this, "tcp://192.168.200.115:1883");

            try {
                mqttClient.connect(arrayOf<String>("mydata/greenhouse"));
            } catch(e: Exception) {
                e.printStackTrace();
            }
        }

        water.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        var data: String = "";
        if (v?.id == R.id.water) {
            data = "water_on";
            publish(data);
        }
    }

    fun publish(data: String) {
        mqttClient.publish("mydata/greenhouse", data);
    }

}