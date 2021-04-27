package com.example.autofarm.mainfragment

import androidx.appcompat.app.AppCompatActivity

import android.webkit.WebViewClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.example.autofarm.R
import kotlinx.android.synthetic.main.farmcctv.*

class FarmCctv : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.farmcctv, container, false);
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        farmcamera.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        // 자신의 아이피 주소를 입력해야 합니다.
        farmcamera.loadUrl("http://192.168.200.118:5000/showVideo")



    }

}