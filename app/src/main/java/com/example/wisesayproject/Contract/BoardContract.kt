package com.example.wisesayproject.Contract

import com.example.wisesayproject.DTO.Board

interface BoardContract {

    interface  View {
        fun waringShackBar(string : String)
        fun goUserSayBoxList(list : List<Board>)
    }
    interface  Presenter{
        fun setView(view : View)
        fun wiseSayingWrite(userNickname:String,content : String, temp_ImageName : String ,feel:String)
        fun wiseSayingListGet(userNickname: String)
        fun likewiseSaying( bno : Int, userid : String) // like 업데이트
    }

    interface InfoDataSource {

        interface LoadInfoCallback {
            fun onInfoLoaded(ss : String)
            fun onDataNotAvailable(ff : String)
            fun onListInfoLoaded(list : List<Board>)
        }
    }
}