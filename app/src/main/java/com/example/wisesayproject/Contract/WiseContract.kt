package com.example.wisesayproject.Contract

import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying

interface WiseContract {

    interface  View {
        fun returnGetWise(wise : WiseSaying)
        fun showToast(text : String)
        fun returnsaveWiseList(list : List<SaveWise>)
    }

    interface  Presenter{

        fun setView(view : View)
        fun getWise(feel : String)
        fun getUserWise()
        fun saveWise(writeuserNickname:String ,content : String , userNickname:String , myquestion : String)
        fun getsaveWiseList(userNickname : String)
    }

    interface InfoDataSource {

        interface LoadInfoCallback {
            fun onInfoLoaded(wise : WiseSaying)
            fun onDataNotAvailable(ff : String)
            fun onsaveInfoLoaded(ss : String)
        }

        interface LoadInfoCallback2 {
            fun onsaveListInfoLoaded(list : List<SaveWise>)
        }
    }


}