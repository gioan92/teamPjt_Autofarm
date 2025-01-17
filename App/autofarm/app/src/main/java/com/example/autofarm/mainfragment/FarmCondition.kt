package com.example.autofarm.mainfragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.*
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
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
import java.util.*
import kotlin.concurrent.thread

class FarmCondition : Fragment() {
    lateinit var mqttClient1: MyMqtt
    lateinit var handler1: Handler
    var data:String =  ""
    var hum:Double = 0.0
    var degree:Double = 0.0
    var flag:Int = 0
    var cds:Int = 0
    var fire:Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmstate, container, false);

        handler1 = object : Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if(data[0] == '1')
                {
                    var dhtdata = data.substring(2)
                    Log.d("mydata", dhtdata)
                    var datalist = dhtdata.split(",")
                    var hum1 = datalist[0]
                    var degree1 = datalist[1]

                    hum = hum1.toDouble()
                    degree = degree1.toDouble()

                    degreeoutput.text = degree.toString() + "°C"
                    humidityoutput.text = hum.toString() + "%"
                    //열선, 쿨러 수동 조절

                    //물 급여조절
                    if(hum < 70.0 && flag == 0){
                        //publish("water_on");
                        flag = 1
                        Thread.sleep(5000L)
                        publish("water_off");
                    }
                    else{
                        publish("water_off");
                        //Thread.sleep(60000L)
                        flag = 0
                    }

                }
                else if(data[0] == '2') {
                    var arddata = data.substring(2)
                    Log.d("mydata", arddata)
                    lightoutput.text = arddata.toString()
                    //채광량제어
                    var light = arddata.toDoubleOrNull()
                    if(light != null) {
                        if (light > 500.0) {
                            publish("light_up");
                        } else {
                            publish("light_down");
                        }
                    }
                }
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
            mqttClient1 = MyMqtt(context!!, "tcp://192.168.7.209:1883")
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
        Log.d("mydata", data)
        // handler1.sendMessage(handler1.obtainMessage())

        if(data[0] == '1')
        {
            var dhtdata = data.substring(2)
            Log.d("mydata", dhtdata)

            var datalist = dhtdata.split(",")
            var hum1 = datalist[0]
            var degree1 = datalist[1]
            hum = hum1.toDouble()
            degree = degree1.toDouble()

            degreeoutput.text = degree.toString() + "°C"
            humidityoutput.text = hum.toString() + "%"
        }
        else if(data[0] == '2')
        {
            var arddata = data.substring(2)

            var ardlist = arddata.split(",")
            var cds1 = ardlist[0]
            var fire1 = ardlist[1]

            cds = cds1.toInt()


            lightoutput.text = cds.toString()
            fire = fire1.toInt()


            if(fire < 60){
                ceil.text = "안전"
            }

            if(fire > 60)
            {
                ceil.text = "화재 발생"

                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.warning)
                var builder = NotificationCompat.Builder(context!!, "1111")
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle("경고")
                        .setContentText("화재 발생")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setLargeIcon(bitmap)
                        .setDefaults(
                                Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE or
                                        Notification.DEFAULT_LIGHTS)

                val style = NotificationCompat.BigPictureStyle(builder)
                style.bigPicture(bitmap)
                builder.setStyle(style)
                createNotiChannel(builder, "1111")
            }
        }
    }

    fun publish(data:String){
        mqttClient1.publish("mydata/greenhouse",data)
    }



    fun createNotiChannel(builder: NotificationCompat.Builder, id:String){
        // 낮은 버전의 사용자에 대한 설정

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel =
                    NotificationChannel(id, "mynetworkchannel", NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(Integer.parseInt(id), builder.build())
        }else{
            // 이전버전인 경우
            val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(Integer.parseInt(id), builder.build())
        }
    }
}