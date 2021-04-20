package com.example.autofarm.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.autofarm.MainActivity
import com.example.autofarm.R
import kotlinx.android.synthetic.main.login.*
import okhttp3.*
import org.json.JSONObject
import kotlin.concurrent.thread
import kotlin.reflect.typeOf

class LoginActivity : AppCompatActivity() {
    lateinit var handler1: Handler
    lateinit var handler2: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        login_btn.setOnClickListener{
            handler1 = object : Handler(Looper.myLooper()!!){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    //여기에서 스레드에서 전달한 값으로 혹은 스레드에서 변경한 값으로 화면을 변경하는 작업
                    Toast.makeText(this@LoginActivity, "로그인 완료", Toast.LENGTH_SHORT).show()
                    val firstIntent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(firstIntent)
                }
            }
            handler2 = object : Handler(Looper.myLooper()!!){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    //여기에서 스레드에서 전달한 값으로 혹은 스레드에서 변경한 값으로 화면을 변경하는 작업
                    Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
            thread {
                var jsonobj = JSONObject()
                jsonobj.put("u_id", user_id.text)
                jsonobj.put("u_pwd", user_pwd.text)
                val json:String = jsonobj.toString()

                val site = "http://192.168.0.197:8000/androidlogin"
                val client = OkHttpClient()

                val request: Request = Request.Builder().url(site)
                                              .post(RequestBody.create(MediaType.parse("application/json"),json)).build()

                val response: Response = client.newCall(request).execute()
                val result = response.body()!!.string()
                Log.d("msg", "gg"+result)
                var data = result.split('"')
                if (data[1].equals("ok")) {
                    handler1.sendMessage(handler1.obtainMessage())
                } else {
                    handler2.sendMessage(handler2.obtainMessage())
                }
            }
        }
    }
}