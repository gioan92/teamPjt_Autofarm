package com.example.autofarm.mainfragment

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autofarm.MainActivity
import com.example.autofarm.R
import com.example.autofarm.mqtt.MyMqtt
import kotlinx.android.synthetic.main.farm_main.*
import kotlinx.android.synthetic.main.farmstate.*
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.math.log

class FarmCondition : Fragment() {
    var dhtdata:String? = null
    lateinit var handler1: Handler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmstate, container, false);
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler1 = object :Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {
                // 화면을 변경하는 작업을 한다.
                when(msg.what){
                    0 -> degree.text = msg.arg1.toString()
                }
            }
        }
        val parent = activity as MainActivity
        dhtdata = parent.senddata()

        var sample:String = "20"
        Log.d("mymqtt", "transport$dhtdata")

        degree.setText(dhtdata)
    }

    fun useMessageHandler(view: View){
        var parent = activity as MainActivity
        //parent.test101.setText("adf")
        var dhtdata = (activity as MainActivity).senddata()

        Log.d("mymqtt", "transport dhtdata")

        var dhtlist = dhtdata.split(",")

        var hum1 = dhtlist[0]
        var degree1 = dhtlist[1]

        var hum2 = hum1.toFloat()
        var degree2 = degree1.toFloat()

        var hum = hum2.toInt()
        var degree = degree2.toInt()

        Log.d("mymqtt", "transport hum $hum  degree $degree")

        thread {
            for(i in 1..100){

                val mymsg = Message()
                mymsg.what = 0
                mymsg.arg1 = hum

                handler1.sendMessage(mymsg)

                SystemClock.sleep(3000)
            }
        }
    }
}