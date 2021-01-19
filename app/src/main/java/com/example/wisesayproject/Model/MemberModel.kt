package com.example.wisesayproject.Model

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.wisesayproject.Activity.LoginActivity
import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.DTO.User
import com.example.wisesayproject.RetrofitService.NetRetrofit
import com.example.wisesayproject.RetrofitService.MemberRetrofitService
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MemberModel (presenter : MemberContract.Presenter) : MemberContract.InfoDataSource {

    private  var retrofit : Retrofit = NetRetrofit.getInstance()
    private  var mservice : MemberRetrofitService = retrofit.create(MemberRetrofitService::class.java)

    fun MemberJoin(userNickname : String , passWord : String , callback: MemberContract.InfoDataSource.LoadInfoCallback){
        Log.v("wgeg3", userNickname + passWord)// 값 넘어오는거 확인
        var dto = User(userNickname , passWord) //  user dto 만든거임
        var gson = Gson()

        var objJson : String = gson.toJson(dto)
        mservice.memberJoin(objJson).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("@@@","실패 : {$t}")
                callback.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                callback.onInfoLoaded("성공")
            }
        })
    }

    fun MemberLogin(userNickname: String, passWord: String, callback: MemberContract.InfoDataSource.LoadInfoCallback){
        Log.v("wgeg3", userNickname + passWord)// 값 넘어오는거 확인
        var dto = User(userNickname , passWord) //  user dto 만든거임
        var gson = Gson()
        var objJson : String = gson.toJson(dto)
        mservice.memberLogin(objJson).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("@@@","실패 : {$t}")
                callback.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.v("llloggg", response.body().toString())
                if(response.body()?.string().equals("success")){
                    callback.onInfoLoaded("성공")
                }else{
                    callback.onInfoLoaded("정보없음")
                }
            }
        })
    }

    fun getmemberLikeCount(userNickname: String , callback: MemberContract.InfoDataSource.LoadInfoCallback){
        var dto = User(userNickname , "") //  user dto 만든거임
        var gson = Gson()
        var objJson : String = gson.toJson(dto)
        mservice.getmemberLikeCount(objJson).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("@@@","실패 : {$t}")
                callback.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                Log.v("llloggg", response.body().toString())
                    callback.onInfoLoaded(response.body().toString())
            }
        })
    }
}