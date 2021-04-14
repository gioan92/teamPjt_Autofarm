package com.example.autofarm.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.autofarm.R
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
//      버튼을 누르면 아이디와 비번을 레이아웃에서 가져온다
//      select with id 로 user 테이블에서 가져온다
//      if 디비비번 == 레이아웃비번
//          로그인 성공, preference에 저장, 메인화면으로 이동
//      else
//          로그인 실패 다이얼로그

//        login_btn.setOnClickListener {
//            MConnectionManager.executeSql(
//                "url",
//                "user",
//                "pw",
//                "query",
//                { rs ->
//                    CustomerInfo(
//                        rs.getInt("code"),
//                        rs.getString("name")
//                    )
//                }
//            ) { items, msg ->
//                if (items == null) {
//                    AlertDialog.Builder(this)
//                        .setMessage(msg)
//                        .show()
//                } else {
//                    adapter.items = items
//                }
//            }
//        }
    }
}