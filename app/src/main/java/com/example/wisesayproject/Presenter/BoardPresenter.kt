package com.example.wisesayproject.Presenter

import android.util.Log
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.Model.BoardModel
import com.example.wisesayproject.Model.MemberModel

class BoardPresenter : BoardContract.Presenter{

    private var BoardView : BoardContract.View? = null
    private var BoardModel : BoardModel? =null

    override fun setView(view : BoardContract.View){ // view랑 붙이는 작업
        BoardView = view
        BoardModel = BoardModel()
    }

    override fun wiseSayingWrite(userNickname : String ,content: String) {
        BoardModel?.wiseSayingWrite(content,userNickname)
    }




}