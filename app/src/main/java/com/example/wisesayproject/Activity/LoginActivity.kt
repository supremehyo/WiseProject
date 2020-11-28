package com.example.wisesayproject.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.Presenter.MemberPresenter
import com.example.wisesayproject.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), MemberContract.View  {

    var nickname : String?=null
    var passWord : String?=null
    lateinit var  view2 : View
    lateinit var imm : InputMethodManager
    private  lateinit  var  mpresenter: MemberPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mpresenter = MemberPresenter()
        mpresenter.setView(this)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btn_loginComplete.setOnClickListener {view ->
            view2= view
            nickname = editId.text.toString()
            passWord = editPassWord.text.toString()
            if(nickname == "" || passWord == ""){
                imm.hideSoftInputFromWindow( loginLin.windowToken ,0)
                Snackbar.make(view ,"입력 하지않은 값이 있어요." ,Snackbar.LENGTH_SHORT).setBackgroundTint(resources.getColor(R.color.colorPrimary)).show()
            }else{
                mpresenter.memberLogin(nickname!! , passWord!!)
            }
        }
    }

    override fun goMain2() {
          val LoginIntent = Intent(this, MainActivity2::class.java)
          startActivity(LoginIntent)
          finish()
    }

    override fun waringShackBar() {
        imm.hideSoftInputFromWindow( loginLin.windowToken ,0)
        Snackbar.make(view2 ,"로그인에 실패했습니다." ,Snackbar.LENGTH_SHORT).setBackgroundTint(resources.getColor(R.color.colorPrimary)).show()
    }

    override fun waringShackBar2() {
        imm.hideSoftInputFromWindow( loginLin.windowToken ,0)
        Snackbar.make(view2 ,"네트워크 통신에 실패했습니다." ,Snackbar.LENGTH_SHORT).setBackgroundTint(resources.getColor(R.color.colorPrimary)).show()
    }
}