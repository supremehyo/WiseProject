package com.example.wisesayproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.Toast
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.Presenter.BoardPresenter
import com.example.wisesayproject.Presenter.MemberPresenter
import com.example.wisesayproject.R
import com.example.wisesayproject.UserSayBoxAdapter
import kotlinx.android.synthetic.main.activity_user_saying_box.*
import kotlinx.android.synthetic.main.activity_user_saying_box_detail.*

class UserSayingBoxActivity : AppCompatActivity() , BoardContract.View , MemberContract.View{


    lateinit var gridView: GridView

    private  lateinit  var bpresenter : BoardPresenter
    private lateinit var  mpresenter : MemberPresenter
    var heartCount : Int = 0;
    private lateinit var  userid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_saying_box)

        val intent = intent.extras
        var name = intent!!.getString("name") // 아이디가 넘어옴
        if (name != null) {
            userid = name
        }
        gridView = findViewById(R.id.usersayGrid)


        bpresenter = BoardPresenter()
        bpresenter.setView(this)

        mpresenter = MemberPresenter()
        mpresenter.setView(this)


        if (name != null) { // 아이디가 null 이 아니면
            bpresenter.wiseSayingListGet(name) // 해당 id로 그사람이 쓴 usersay를 다 가져옴
            mpresenter.getmemberLikeCount(name)
        }


    }
    override fun waringShackBar(string: String) {
        Toast.makeText(this,string, Toast.LENGTH_LONG).show() // 적었던 글이 없어요
    }
    override fun goUserSayBoxList(list: List<Board>) {
        gridView.adapter =UserSayBoxAdapter(applicationContext ,list)
    }
    override fun goMain2() {

    }
    override fun waringShackBar() {

    }
    override fun waringShackBar2(ss : String) {
        MyHeart.text = "받은 하트 수는"+ss+"개 입니다!"
    }
}