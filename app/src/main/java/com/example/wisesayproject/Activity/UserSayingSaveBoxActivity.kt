package com.example.wisesayproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import com.example.wisesayproject.Contract.WiseContract
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying
import com.example.wisesayproject.Presenter.WisePresenter
import com.example.wisesayproject.R
import com.example.wisesayproject.UserSaySaveBoxAdapter

class UserSayingSaveBoxActivity : AppCompatActivity() , WiseContract.View {

    lateinit var gridView: GridView
    private  lateinit  var  wpresenter: WisePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_saying_save_box)
        gridView = findViewById(R.id.usersaySaveGrid)
        wpresenter = WisePresenter()
        wpresenter.setView(this)

        val intent = intent.extras
        var name = intent!!.getString("name") // 아이디가 넘어옴

        if (name != null) {
            wpresenter.getsaveWiseList(name)
        }

    }

    override fun returnGetWise(wise: WiseSaying) {

    }
    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
    override fun returnsaveWiseList(list: List<SaveWise>) {
        gridView.adapter = UserSaySaveBoxAdapter(applicationContext,list)
    }
}