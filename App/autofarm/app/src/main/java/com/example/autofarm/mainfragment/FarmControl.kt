package com.example.autofarm.mainfragment

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.autofarm.MainActivity
import com.example.autofarm.R
import com.example.autofarm.mainfragment.control.CeilActivity
import com.example.autofarm.mainfragment.control.DegreeActivity
import com.example.autofarm.mainfragment.control.LightActivity
import com.example.autofarm.mainfragment.control.WaterActivity
import kotlinx.android.synthetic.main.farmcontrol.*

class FarmControl() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmcontrol, container, false);
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
    }

}