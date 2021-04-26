package com.example.autofarm.mainfragment.control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.carcontrol.*
import java.lang.Exception

class CarActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mqttClient: MyMqtt;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.carcontrol);
        mqttClient = MyMqtt(this,"tcp://192.168.200.101:1883");
        try{
            mqttClient.connect(arrayOf<String>("iot/#"));
        }
        catch (e: Exception){
            e.printStackTrace();
        }

        var carIntent = intent;
        var tractorNum = carIntent.getIntExtra("tractor", 1);
        tractornum.text = "tractor${tractorNum.toString()}";

        autocontrol.setOnClickListener {
            frontbutton.visibility = View.INVISIBLE;
            leftbutton.visibility = View.INVISIBLE;
            rightbutton.visibility = View.INVISIBLE;
            backbutton.visibility = View.INVISIBLE;
            stopbutton.visibility = View.INVISIBLE;
            farmcall.visibility = View.VISIBLE;
            warehousecall.visibility = View.VISIBLE;
            publish("a");
        }

        manualcontrol.setOnClickListener {
            frontbutton.visibility = View.VISIBLE;
            leftbutton.visibility = View.VISIBLE;
            rightbutton.visibility = View.VISIBLE;
            backbutton.visibility = View.VISIBLE;
            stopbutton.visibility = View.VISIBLE;
            farmcall.visibility = View.INVISIBLE;
            warehousecall.visibility = View.INVISIBLE;
            publish("m");
        }

        farmcall.setOnClickListener {
            publish("p");
        }

        warehousecall.setOnClickListener {
            publish("w");
        }

        frontbutton.setOnClickListener(this);
        leftbutton.setOnClickListener(this);
        rightbutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        stopbutton.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.frontbutton) {
            publish("f");
        }
        else if (v?.id == R.id.leftbutton) {
            publish("l");
        }
        else if (v?.id == R.id.rightbutton) {
            publish("r");
        }
        else if (v?.id == R.id.backbutton) {
            publish("b");
        }
        else if (v?.id == R.id.stopbutton) {
            publish("s");
        }
    }

    fun publish(data:String){
        mqttClient.publish("mydata/greenhouse",data)
    }
}