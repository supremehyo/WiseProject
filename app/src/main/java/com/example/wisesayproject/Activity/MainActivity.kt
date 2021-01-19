package com.example.wisesayproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val prefs: SharedPreferences = getSharedPreferences("first", Context.MODE_PRIVATE)
        prefs.edit().putInt("prefs",1)

        val prefs2: SharedPreferences =  getSharedPreferences("LoginNickname", Context.MODE_PRIVATE)

        if(prefs2.getString("LoginNickname","") != null){
            val MainActivity2Intent = Intent(this, MainActivity2::class.java)
            startActivity(MainActivity2Intent)
            finish()
        }

        btn_login.setOnClickListener{
            val LoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(LoginIntent)
        }
        btn_signup.setOnClickListener{
            val JoinIntent = Intent(this,JoinActivity::class.java)
            startActivity(JoinIntent)
        }

    }
}


