package com.example.wisesayproject.Activity

import android.app.Activity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.wisesayproject.Adapter.RegisterPagerAdapter
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_2.view.*

class MainActivity2 : AppCompatActivity() {
    private val activity: Activity? = null
    var lastTimeBackPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        register_viewpager.adapter = RegisterPagerAdapter(supportFragmentManager)
        register_viewpager.offscreenPageLimit = 2
        register_viewpager.setCurrentItem(1)
        dots_indicator.setViewPager(register_viewpager)

        linlin.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                Log.d("cccclidsfsfnk" , "롱클릭")

                return true
            }

        })

    }



    override fun onBackPressed()
    {
        if(System.currentTimeMillis() - lastTimeBackPressed >= 1500){
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show() }
        else {
            ActivityCompat.finishAffinity(this)
            System.runFinalization()
            System.exit(0)
        }
    }
}