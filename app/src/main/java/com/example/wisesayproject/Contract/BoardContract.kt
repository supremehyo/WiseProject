package com.example.wisesayproject.Contract

interface BoardContract {

    interface  View {

    }
    interface  Presenter{
        fun setView(view : View)
        fun wiseSayingWrite(userNickname:String,content : String)
    }
}