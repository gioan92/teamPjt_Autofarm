package com.example.autofarm.mainfragment.control

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.autofarm.R
import kotlinx.android.synthetic.main.farmmap.*

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmmap);

        visibleTestBtn.setOnClickListener {
            tractor1_warehouse.visibility = View.VISIBLE;
        }
        tractor1_warehouse.setOnClickListener {
            visibleTestBtn.setText("tractor1 클릭함");
            val carIntent = Intent(this, CarActivity::class.java).apply {
            } // **Tractor별 Intent로 데이터 다르게 보내서 연결할 raspberrypi IP주소 다르게 설정하기**
            startActivity(carIntent);
        }
    }

}