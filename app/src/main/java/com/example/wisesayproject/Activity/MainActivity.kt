package com.example.wisesayproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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


