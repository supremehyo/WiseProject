package com.example.wisesayproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.Presenter.BoardPresenter
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.activity_wise_say_write.*

class WiseSayWriteActivity : AppCompatActivity() , BoardContract.View{

    var content : String = ""

    private  lateinit  var bpresenter : BoardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wise_say_write)


        bpresenter = BoardPresenter()
        bpresenter.setView(this)

        writebutton.setOnClickListener {
            content = editContent.text.toString()
            bpresenter.wiseSayingWrite("아이디", content)
        }

    }
}