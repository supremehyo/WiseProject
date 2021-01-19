package com.example.wisesayproject.Presenter

import android.util.Log
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.Model.MemberModel

class MemberPresenter : MemberContract.Presenter , MemberContract.InfoDataSource{

    private var MemberView : MemberContract.View? = null
    private var MemberModel : MemberModel? =null

    override fun setView(view : MemberContract.View){ // view랑 붙이는 작업
        MemberView = view
        MemberModel = MemberModel(this)
    }

    override fun memberJoin( userNickname: String, passWord: String) {
        Log.v("wgeg2", userNickname + passWord)
        MemberModel?.MemberJoin(userNickname , passWord ,object : MemberContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(a : String) {
                if(a.equals("성공")){
                    MemberView?.goMain2()
                }else if(a.equals("정보없음")){
                    MemberView?.waringShackBar()
                }
            }
            override fun onDataNotAvailable(ff : String) {
                Log.v("ffffail" , "실패패ㅐ패패") // 콜백 방식으로 잘 작동하는거 확인
            }
        }) // 모델에 가입 요청 보냄
    }

    override fun memberLogin(userNickname: String, passWord: String ) {
        MemberModel?.MemberLogin(userNickname , passWord ,object : MemberContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(ss: String) {
                if(ss.equals("성공")){
                    MemberView?.goMain2()
                }else if(ss.equals("정보없음")){
                    MemberView?.waringShackBar()
                }
            }
            override fun onDataNotAvailable(ff : String) {
                if(ff.equals("실패")) {
                    MemberView?.waringShackBar2("네트워크 통신에 실패했습니다.")
                }
            }
        }) // 로그인 요청 보냄
    }

    override fun getmemberLikeCount(userNickname: String) {
        MemberModel?.getmemberLikeCount(userNickname  , object : MemberContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(ss: String) {
                MemberView?.waringShackBar2(ss)
            }

            override fun onDataNotAvailable(ff: String) {

            }
        })
    }
}