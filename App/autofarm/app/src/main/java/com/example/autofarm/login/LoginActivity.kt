package com.example.autofarm.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.autofarm.R
import kotlinx.android.synthetic.main.login.*
import okhttp3.*
import org.json.JSONObject
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        login_btn.setOnClickListener{
            thread {
                var jsonobj = JSONObject()
                jsonobj.put("userid", user_id.text)
                jsonobj.put("userpwd", user_pwd.text)
                val json:String = jsonobj.toString()

                val site = "http://192.168.0.197:8000/login"
                val client = OkHttpClient()

                val request: Request = Request.Builder().url(site)
                                              .post(RequestBody.create(MediaType.parse("application/json"),json)).build()

                val response: Response = client.newCall(request).execute()
                val result = response.body()!!.string()
                Log.d("msg",result)
            }
        }
    }
}