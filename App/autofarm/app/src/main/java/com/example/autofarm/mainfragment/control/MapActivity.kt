package com.example.autofarm.mainfragment.control

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.example.autofarm.R
import kotlinx.android.synthetic.main.farmmap.*

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmmap);

        visibleTestBtn.setOnClickListener {
            tractor1_warehouse.visibility = View.VISIBLE;
        }
    }

}