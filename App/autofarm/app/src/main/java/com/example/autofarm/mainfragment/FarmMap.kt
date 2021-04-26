package com.example.autofarm.mainfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autofarm.R
import com.example.autofarm.mainfragment.control.CarActivity
import kotlinx.android.synthetic.main.farmmap.*

class FarmMap : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.farmmap);
//
//        visibleTestBtn.setOnClickListener {
//            tractor1_warehouse.visibility = View.VISIBLE;
//        }
//        tractor1_warehouse.setOnClickListener {
//            visibleTestBtn.setText("tractor1 클릭함");
//            val carIntent = Intent(this, CarActivity::class.java).apply {
//            } // **Tractor별 Intent로 데이터 다르게 보내서 연결할 raspberrypi IP주소 다르게 설정하기**
//            startActivity(carIntent);
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmmap, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        visibleTestBtn.setOnClickListener {
            tractor1_warehouse.visibility = View.VISIBLE;
        }
        tractor1_warehouse.setOnClickListener {
            visibleTestBtn.setText("tractor1 클릭함");
            activity?.let {
                val intent = Intent(context, CarActivity::class.java).apply {
                    putExtra("tractor", 1);
                }
                startActivity(intent)
            }
        }
    }
}