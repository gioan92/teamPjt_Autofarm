package com.example.autofarm

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.autofarm.mainfragment.*
import com.example.autofarm.mainfragment.control.DegreeActivity
import com.example.autofarm.mqtt.MyMqtt
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.farm_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList





class MainActivity : AppCompatActivity() {
    //Fragment를 불러오기 위한 변수
    var frag_condition = FarmCondition();
    var frag_cctv = FarmCctv();
    var frag_control = FarmControl();
    var fragmentlist = ArrayList<Fragment>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farm_main)

        setActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        tabs.setTabTextColors(Color.BLACK, Color.BLUE);

        fragmentlist.add(frag_condition);
        fragmentlist.add(frag_cctv);
        fragmentlist.add(frag_control);

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentlist.size;
            }
            override fun createFragment(position: Int): Fragment {
                return fragmentlist[position];
            }

        }

        //Fragment와 Tab을 매칭
        viewpager.adapter = adapter;
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "농장현황";
                1 -> tab.text = "농장모습";
                2 -> tab.text = "농장제어";
            }
        }.attach();
    }

}