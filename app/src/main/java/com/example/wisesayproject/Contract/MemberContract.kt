package com.example.wisesayproject.Contract

interface MemberContract {

    interface  View {
        fun goMain2()
        fun waringShackBar()
        fun waringShackBar2()
    }

    interface  Presenter{

        fun setView(view : View)
       fun memberJoin(userNickname:String , passWord :String)
        fun memberLogin(userNickname:String , passWord :String)
    }

    interface InfoDataSource {

        interface LoadInfoCallback {
            fun onInfoLoaded(ss : String)
            fun onDataNotAvailable(ff : String)
        }
    }

}