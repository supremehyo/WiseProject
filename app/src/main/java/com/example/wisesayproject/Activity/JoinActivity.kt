package com.example.wisesayproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.Presenter.MemberPresenter
import com.example.wisesayproject.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_join.editId
import kotlinx.android.synthetic.main.activity_join.editPassWord
import kotlinx.android.synthetic.main.activity_login.*

class JoinActivity : AppCompatActivity(), MemberContract.View {

    // https://velog.io/@tornbolo/Kotlin 레트로핏 구현 참고
    var Id : String=""
    var PassWord : String = ""
    var PassWordCheck : String = ""
    lateinit var imm : InputMethodManager
    private  lateinit  var  mpresenter: MemberPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        
        mpresenter = MemberPresenter() // 초기화
        mpresenter.setView(this) // view 붙이기
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btn_joinComplete.setOnClickListener {view ->
            Id = editId.text.toString()
            PassWord = editPassWord.text.toString()
            PassWordCheck  = editPassWordCheck.text.toString()
            if(Id == "" || PassWord == "" || PassWordCheck == ""){
                imm.hideSoftInputFromWindow( joinLin.windowToken ,0)
                Snackbar.make(view ,"입력 하지않은 값이 있어요." , Snackbar.LENGTH_SHORT).setBackgroundTint(resources.getColor(R.color.colorPrimary)).show()
            }else if(Id !="" && (PassWord ==  PassWordCheck)){
                mpresenter.memberJoin(Id,PassWord)
            }else if(Id !="" && (PassWord != PassWordCheck)){
                Snackbar.make(view ,"비밀번호를 확인해주세요." , Snackbar.LENGTH_SHORT).setBackgroundTint(resources.getColor(R.color.colorPrimary)).show()
            }
        }
    }

    override fun goMain2() {
        val LoginIntent = Intent(this, MainActivity2::class.java)
        startActivity(LoginIntent)
        finish()
    }

    override fun waringShackBar() {

    }

    override fun waringShackBar2() {

    }
}