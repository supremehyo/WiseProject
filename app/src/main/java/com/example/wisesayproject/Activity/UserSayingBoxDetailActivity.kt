package com.example.wisesayproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.activity_user_saying_box_detail.*

class UserSayingBoxDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_saying_box_detail)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE); //캡처방지 소스
        val intent = intent.extras
        ContentText.text = intent!!.getString("content")
        username.text = intent!!.getString("name")
    }
}